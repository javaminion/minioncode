package com.usecase.reportgen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.usecase.reportgen.document.AlphaVantageReport;
import com.usecase.reportgen.document.ComputedFact;
import com.usecase.reportgen.document.ConfigMap;
import com.usecase.reportgen.document.Fact;
import com.usecase.reportgen.document.ReportDetail;
import com.usecase.reportgen.document.ReportTrail;
import com.usecase.reportgen.service.AdminService;
import com.usecase.reportgen.service.PythonService;
import com.usecase.reportgen.service.ReportGenService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class GUIController {
	
    private static final Logger logger = LoggerFactory.getLogger(GUIController.class);
	
	@Autowired
	PythonService pyService;
	
	@Autowired
	ReportGenService reportGenService;
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/")
    public String login(Model model) {
        return "login";
    }
	
	 @PostMapping("/login")
	public String validateUser(@RequestParam("username") String username,
	                              @RequestParam("password") String password,
	                              Model model) {
		 String authString = username + ":" + password; 
		 String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
		 
		 String expected = (String) fetchValueForKey("logininfo", "password");
		 
		 String redirect = (encodedAuthString.equals(expected))?"redirect:/ui":"login";
		 
		 return redirect;
	}
	

    @GetMapping("/ui")
    public String showForm(Model model) {
    	List<ReportDetail> reports = reportGenService.fetchAllReports();
    	reports = reports.stream().sorted((r1, r2) -> r2.getTimestamp().compareTo(r1.getTimestamp())).collect(Collectors.toList());
    	model.addAttribute("reportDetails", reports);
        return "input";
    }
    
    @GetMapping("/report/trail")
    public String reportTrail(@RequestParam("id") String id, Model model) {
    	List<ReportTrail> reportTrailRecords = reportGenService.fetchReportTrailByDocumentRef(id);
    	model.addAttribute("reportTrailRecords", reportTrailRecords);
    	model.addAttribute("id", id);
        return "reporttrail";
    }

    @PostMapping("/ui/process")
    public String processForm(Model model) {
    	
    	String uniqueId = UUID.randomUUID().toString(); 
    	logger.info("Generated Custom Unique ID: " + uniqueId);
    	
    	logger.info("Calling the Python Service..");
        
    	//String response = pyService.fetchData(cik, topic);
    	
    	String cikVal = (String) fetchValueForKey("companyinfo", "cik");
    	List<String> topics = (List<String>) fetchValueForKey("secedgartopics", "topics");
    	
    	reportGenService.saveReportDetail(uniqueId, cikVal, null);
    	reportGenService.recordReportTrail(uniqueId, cikVal, null, "Report Initiated", LocalDateTime.now(), "NEW");
    	
    	logger.info("Retrieved Params:");
    	logger.info(cikVal);
    	logger.info("topics size:" + topics.size());
    	
    	boolean callFunction = (!cikVal.isEmpty()) & (!topics.isEmpty());
    	
    	if(callFunction) {
    		
    	  reportGenService.updateRecordDetail(uniqueId, "IN PROGRESS");
    	
    	  reportGenService.recordReportTrail(uniqueId, cikVal, null, "Data Fetch from SEC EDGAR API", LocalDateTime.now(), "IN PROGRESS");
		  String response1 = pyService.sendPostRequestToSecEdgar(uniqueId, cikVal, topics);
		  
		  model.addAttribute(response1);
		  
		  logger.info("Response:" + response1);
		  reportGenService.recordReportTrail(uniqueId, cikVal, null, "Data Processing Completed", LocalDateTime.now(), "DONE");
    	}else {
    		logger.info("Cannot Invoke GCP Function for SEC EDGAR API. Cross Check Parameters");
    	}
		  
		  String symbol = (String) fetchValueForKey("companyinfo", "symbol");
		  String function = (String) fetchValueForKey("alphavantagedetails", "function");
		  String apiKey = (String) fetchValueForKey("alphavantagedetails", "apikey");
		  String cutoff = (String) fetchValueForKey("alphavantagedetails", "cutoff");
		  List<String> filter = (List<String>) fetchValueForKey("alphavantagedetails", "filter");
		  
		  boolean invokeFunction = (!symbol.isEmpty()) & (!function.isEmpty()) & (!apiKey.isEmpty()) & (!cutoff.isEmpty())  & (!filter.isEmpty());
		  
		  if(invokeFunction) {
		  
		  reportGenService.recordReportTrail(uniqueId, cikVal, null, "Data Fetch from Alpha Vantage API", LocalDateTime.now(), "IN PROGRESS");
		  String response2 = pyService.sendPostRequestToAlphaVantage(uniqueId, symbol, function, apiKey, cutoff, filter);
		  
		  model.addAttribute(response2);
		  
		  logger.info("Response:" + response2);
		  reportGenService.recordReportTrail(uniqueId, cikVal, null, "Data Processing Completed", LocalDateTime.now(), "DONE");
		  }else {
			  logger.info("Cannot Invoke GCP Function for Alpha Vantage API. Cross Check Parameters");
		  }

        return "redirect:/ui"; // You can change this to return a different view
    }
    
    public Object fetchValueForKey(String key, String node) {
    	Object value = null;
    	String jsonString = (String) adminService.fetchDocumentByStringKey(key).getJsonValue().get("jsonValue");
    	ObjectMapper mapper = new ObjectMapper();
    	
	    try {
				JsonNode jsonNode = mapper.readTree(jsonString);
				value = jsonNode.get(node);
				//logger.info("Extracted Class" + value);
				if(value.getClass().equals(ArrayNode.class)) {
					List<String> topics = mapper.convertValue(value,List.class);
					return topics;
				}else if(value.getClass().equals(TextNode.class)) {
					return mapper.convertValue(value, String.class);
				}
				
		}catch(Exception ex) {
			logger.info("Json Parsing Exception");
			ex.printStackTrace();
		}
    	return value;
    }
    
    @GetMapping("/generateReport")
    public String getReport(@RequestParam("id") String id, Model model) {
        // Prepare the data
    	
    	logger.info("Inside new Generate Report Method");
    	
    	Map<String, Map<Integer, Long>> chartData = new HashMap<>();
        Map<String, String> chartHeadings = new LinkedHashMap<>();
        Map<String, List<Fact>> topicFacts = new HashMap<>();

    	List<String> topics = (List<String>) fetchValueForKey("secedgartopics", "topics");
    	
    	int idx = 1;
    	
    	for(String topic: topics) {
    		List<Fact> factList = reportGenService.fetchFactByDocumentRefAndTopic(id, topic);

    		List<Fact> sortedRecords = factList.stream()
                    .sorted((r1, r2) -> r1.getFiled().compareTo(r2.getFiled()))
                    .collect(Collectors.toList());
    		
    		String formattedTopic = topic.replaceAll("([a-z])([A-Z])", "$1 $2");
    		String chartId = String.valueOf(idx);
    		List<ComputedFact> filteredResults = reportGenService.fetchComputedFactsByDocumentRefAndTopic(id, topic);
    		 Map<Integer, Long> data = new HashMap<Integer, Long>();
    	        
    	        for(ComputedFact factData :filteredResults) {
    	        	data.put(factData.getFy(), factData.getVal());
    	        }
    	    
    	    if(!data.isEmpty() && data.size() > 0) {
        	    logger.info("Added data for " + chartId + " with heading:" + formattedTopic);
        	    chartData.put(chartId, data);
        	    chartHeadings.put(chartId, formattedTopic);
        	    topicFacts.put(chartId, sortedRecords);
    	    }
    	    idx++;
    	}
    	
    	Optional<AlphaVantageReport> alphaVantageData = reportGenService.fatchAlphaVantageData(id);
    	
    	if(alphaVantageData.isPresent()) {
    		logger.info("Data fetched from AlphaVantage recorded data");
    		logger.debug("Annual Reports:"+ alphaVantageData.get().getAnnualReports().size());
    		logger.debug("Quarterly Reports:"+ alphaVantageData.get().getQuarterlyReports().size());
    		
    		model.addAttribute("annualReports", alphaVantageData.get().getAnnualReports());
    		model.addAttribute("quarterlyReports", alphaVantageData.get().getQuarterlyReports());
    	}else {
    		logger.info("Data NOT fetched from AlphaVantage recorded data");
    	}
    	
        model.addAttribute("chartData", chartData);
        model.addAttribute("chartHeadings", chartHeadings);
        model.addAttribute("topicFacts", topicFacts);
	 return "samplereport";
    }
}


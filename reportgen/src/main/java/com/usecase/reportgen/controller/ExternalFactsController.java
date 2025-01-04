package com.usecase.reportgen.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usecase.reportgen.document.AlphaVantageReport;
import com.usecase.reportgen.document.Fact;
import com.usecase.reportgen.document.SecEdgarCompanyFacts;
import com.usecase.reportgen.repository.AlphaVantageEntryRepository;
import com.usecase.reportgen.repository.AlphaVantageReportRepository;
import com.usecase.reportgen.repository.FactRepository;
import com.usecase.reportgen.repository.SecEdgarCompanyFactsRepository;
import com.usecase.reportgen.service.ReportGenService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExternalFactsController {
	
    private static final Logger logger = LoggerFactory.getLogger(ExternalFactsController.class);

    @Autowired
    private FactRepository factRepository;
    
    @Autowired
    private AlphaVantageReportRepository alphaVantageRepository;
    
    @Autowired
    private ReportGenService reportGenService;
    
    @Autowired 
    private ObjectMapper objectMapper;

	/*
	 * @PostMapping("/saveFacts") public SecEdgarCompanyFacts saveFacts(@RequestBody
	 * SecEdgarCompanyFacts secEdgarCompanyFacts) {
	 * logger.info("Save Facts"); return
	 * secEdgarCompanyFactsRepository.save(secEdgarCompanyFacts); }
	 */

    @GetMapping("/getAllFacts")
    public List<Fact> getAllFacts() {
    	logger.info("Get All Facts");
        return factRepository.findAll();
    }
    
    @PostMapping("/saveFacts") public String saveFacts(@RequestBody JsonNode jsonNode) 
    { try 
    	{ 
    		logger.info("Hi from SEC EDGAR Facts Data Recorder");
    		//SecEdgarCompanyFacts companyFacts = new SecEdgarCompanyFacts(); 
    		List<Fact> facts = objectMapper.readValue( jsonNode.asText(), objectMapper.getTypeFactory().constructCollectionType(List.class, Fact.class) ); 
    		//companyFacts.setFacts(facts); 
    		Fact factRecord = facts.get(0);
    		reportGenService.recordReportTrail(factRecord.getDocumentRef(), null, "SEC EDGAR", "Saving Filtered Data", LocalDateTime.now(), "IN PROGRESS");
    		factRepository.saveAll(facts); 
    		reportGenService.recordReportTrail(factRecord.getDocumentRef(), null, "SEC EDGAR", "Saving Filtered Data", LocalDateTime.now(), "DONE");
    		reportGenService.computeFromFacts(facts);
    		return "Saved successfully!"; 
    	} 
    	catch (Exception e) 
    	{ 
    		logger.info("Oops");
    		e.printStackTrace();
    		return "Error saving data: " + e.getMessage(); 
    	}
    }
    
    @PostMapping("/saveReports") public String saveReports(@RequestBody AlphaVantageReport data) 
    { try 
    	{ 
    		logger.info("Hi from Alpha Vantage Data Recorder");
    		
    		reportGenService.recordReportTrail(data.getId(), null, "Alpha Vantage", "Saving Filtered Data", LocalDateTime.now(), "IN PROGRESS");
    		alphaVantageRepository.save(data); 
    		reportGenService.recordReportTrail(data.getId(), null, "Alpha Vantage", "Saving Filtered Data", LocalDateTime.now(), "DONE");
    		//reportGenService.computeFromFacts(facts);
    		reportGenService.updateRecordDetail(data.getId(), "DONE");
    		return "redirect:/ui"; 
    	} 
    	catch (Exception e) 
    	{ 
    		logger.info("Oops");
    		e.printStackTrace();
    		return "Error saving data: " + e.getMessage(); 
    	}
    }
    
}


package com.usecase.reportgen.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.usecase.reportgen.document.AlphaVantageReport;
import com.usecase.reportgen.document.ComputedFact;
import com.usecase.reportgen.document.Fact;
import com.usecase.reportgen.document.ReportDetail;
import com.usecase.reportgen.document.ReportTrail;
import com.usecase.reportgen.repository.AlphaVantageReportRepository;
import com.usecase.reportgen.repository.ComputedFactRepository;
import com.usecase.reportgen.repository.FactRepository;
import com.usecase.reportgen.repository.ReportDetailRepository;
import com.usecase.reportgen.repository.ReportTrailRepository;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class ReportGenService {
	
    private static final Logger logger = LoggerFactory.getLogger(ReportGenService.class);

	
	@Autowired
	private ComputedFactRepository computedFactRepo;
	
	@Autowired
	private ReportDetailRepository reportDetailRepo;
	
	@Autowired
	private ReportTrailRepository reportTrailRepo;
	
	@Autowired
	private FactRepository factRepo;
	
	@Autowired
	private AlphaVantageReportRepository alphaVantageRepo;
	
	@ModelAttribute("reportDetails")
	public List<ReportDetail> fetchAllReports(){
		return reportDetailRepo.findAll();
	}
	
	public String saveReportDetail(String uniqueId, String cik, String topic) {
		logger.info("Saved Record Detail");
		ReportDetail record = new ReportDetail(uniqueId, cik, topic);
		reportDetailRepo.save(record);
		return "OK";
	}
	
	public String updateRecordDetail(String uniqueId, String status) {
		Optional<ReportDetail> record = reportDetailRepo.findById(uniqueId);
		if(record.isPresent()) {
			logger.info("Updated Record Detail with status : " + status);
			ReportDetail updatedRecord = record.get();
			updatedRecord.setTimestamp(LocalDateTime.now());
			updatedRecord.setStatus(status);
			reportDetailRepo.save(updatedRecord);
		}
		return "OK";
	}
	
	public String computeFromFacts(List<Fact> facts) {
		logger.info("Computing Facts");
		
		Set<String> uniqueTopics = facts.stream()
				.map(Fact::getTopic)
				.distinct()
				.collect(Collectors.toSet());
		
		String auditRef = facts.get(0).getDocumentRef();
		
		for(String topic: uniqueTopics) {
			
			this.recordReportTrail(auditRef, null, topic, "Computing from Facts", LocalDateTime.now(), "IN PROGRESS");
			
			Hashtable<Integer, Long> factTable = new Hashtable<Integer, Long>();
			
			List<Fact> subsetOfFacts = facts.stream()
					.filter(p -> p.getTopic().equals(topic))
					.toList();
			
			for(Fact obj: subsetOfFacts) {
				if(factTable.containsKey(obj.getFy())) {
					Long val = factTable.get(obj.getFy());
					Long newVal = val + obj.getVal();
					factTable.put(obj.getFy(), newVal);
				}else {
					factTable.put(obj.getFy(), obj.getVal());
				}
			}
			
			List<ComputedFact> finalComputedFacts = new ArrayList<ComputedFact>();
			
			for (Map.Entry<Integer, Long> entry : factTable.entrySet()) {
	            int key = entry.getKey();
	            long value = entry.getValue();
	            ComputedFact cFact = new ComputedFact();
	            cFact.setFy(key);
	            cFact.setVal(value);
	            cFact.setCikDpcumentRef(subsetOfFacts.get(0).getDocumentRef());
	            cFact.setTopic(topic);
	            finalComputedFacts.add(cFact);
			}
			
			computedFactRepo.saveAll(finalComputedFacts);
			this.recordReportTrail(auditRef, null, topic, "Saving computed Facts", LocalDateTime.now(), "DONE");
		}
		
		return "OK";
	}
	
	public List<ComputedFact> fetchComputedFactsByDocumentRefAndTopic(String id, String topic){
		return computedFactRepo.findByCikDocumentRefAndTopic(id, topic);
	}
	
	public void recordReportTrail(String documentRef, String cik, String topic, String activity, LocalDateTime timestamp,
			String status) {
			ReportTrail audit = new ReportTrail(documentRef, cik, topic, activity, timestamp, status);
			reportTrailRepo.save(audit);
	}
	
	public List<ReportTrail> fetchReportTrailByDocumentRef(String documentRef){
			return reportTrailRepo.findByDocumentRef(documentRef);
	}
	
	public List<Fact> fetchFactByDocumentRefAndTopic(String documentRef, String topic){
			return factRepo.findByDocumentRefAndTopic(documentRef, topic);
	}
	
	public Optional<AlphaVantageReport> fatchAlphaVantageData(String id){
		return alphaVantageRepo.findById(id);
	}

}

package com.usecase.reportgen.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alphavantagereports") // Specify your MongoDB collection name
public class AlphaVantageReport {
	
	@Id
    private String id;
	
	private List<AlphaVantageEntry> annualReports;
	
	private List<AlphaVantageEntry> quarterlyReports;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<AlphaVantageEntry> getAnnualReports() {
		return annualReports;
	}

	public void setAnnualReports(List<AlphaVantageEntry> annualReports) {
		this.annualReports = annualReports;
	}

	public List<AlphaVantageEntry> getQuarterlyReports() {
		return quarterlyReports;
	}

	public void setQuarterlyReports(List<AlphaVantageEntry> quarterlyReports) {
		this.quarterlyReports = quarterlyReports;
	}
	
	

}

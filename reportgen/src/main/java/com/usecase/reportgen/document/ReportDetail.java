package com.usecase.reportgen.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReportDetail {
	
	@Id
	private String id;
	
	private String cik;
	private String topic;
	private LocalDateTime timestamp;
	
	private String status;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCik() {
		return cik;
	}
	public void setCik(String cik) {
		this.cik = cik;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ReportDetail(String id, String cik, String topic) {
		this.id=id;
		this.cik = cik;
		this.topic = topic;
		this.timestamp = LocalDateTime.now();
		this.status = "NEW";
	}
	

}

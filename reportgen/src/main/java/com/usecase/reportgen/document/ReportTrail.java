package com.usecase.reportgen.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReportTrail {
	
	@Id
	private String id;
	
	private String documentRef;
	private String cik;
	private String topic;
		
	private String activity;
	private LocalDateTime timestamp;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocumentRef() {
		return documentRef;
	}

	public void setDocumentRef(String documentRef) {
		this.documentRef = documentRef;
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

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
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

	public ReportTrail(String documentRef, String cik, String topic, String activity, LocalDateTime timestamp,
			String status) {
		super();
		this.documentRef = documentRef;
		this.cik = cik;
		this.topic = topic;
		this.activity = activity;
		this.timestamp = timestamp;
		this.status = status;
	}
	
}

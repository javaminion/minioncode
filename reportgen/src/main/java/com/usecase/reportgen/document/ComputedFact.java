package com.usecase.reportgen.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ComputedFact {
	
	@Id
	private String id;
	
	private String topic;
	
	private String cikDocumentRef;
			
	public String getCikDocumentRef() {
		return cikDocumentRef;
	}
	public void setCikDpcumentRef(String cikDocumentRef) {
		this.cikDocumentRef = cikDocumentRef;
	}
	
	private int fy;
	private long val;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getFy() {
		return fy;
	}
	public void setFy(int fy) {
		this.fy = fy;
	}
	public long getVal() {
		return val;
	}
	public void setVal(long val) {
		this.val = val;
	}
	
	

}

package com.usecase.reportgen.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Fact {
    @Id
    private String id;
    private String start;
    private String end;
    private long val;
    private String accn;
    private int fy;
    private String fp;
    private String form;
    private String filed;
    private String frame;
    private String topic;
    private String documentRef;
    
               
	public String getDocumentRef() {
		return documentRef;
	}
	public void setDocumentRef(String documentRef) {
		this.documentRef = documentRef;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public long getVal() {
		return val;
	}
	public void setVal(long val) {
		this.val = val;
	}
	public String getAccn() {
		return accn;
	}
	public void setAccn(String accn) {
		this.accn = accn;
	}
	public int getFy() {
		return fy;
	}
	public void setFy(int fy) {
		this.fy = fy;
	}
	public String getFp() {
		return fp;
	}
	public void setFp(String fp) {
		this.fp = fp;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getFiled() {
		return filed;
	}
	public void setFiled(String filed) {
		this.filed = filed;
	}
	public String getFrame() {
		return frame;
	}
	public void setFrame(String frame) {
		this.frame = frame;
	}

    // Getters and Setters
	
	@JsonCreator public Fact(@JsonProperty("start") String start, @JsonProperty("end") String end, @JsonProperty("val") long val, @JsonProperty("accn") String accn, @JsonProperty("fy") int fy, @JsonProperty("fp") String fp, @JsonProperty("form") String form, @JsonProperty("filed") String filed, @JsonProperty("frame") String frame, @JsonProperty("topic") String topic, @JsonProperty("documentRef") String documentRef) { this.start = start; this.end = end; this.val = val; this.accn = accn; this.fy = fy; this.fp = fp; this.form = form; this.filed = filed; this.frame = frame; this.topic=topic; this.documentRef = documentRef;}
    
}

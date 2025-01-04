package com.usecase.reportgen.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Document
public class SecEdgarCompanyFacts {
    @Id
    private String id;
    private List<Fact> facts;
    
    // Getters and Setters
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Fact> getFacts() {
		return facts;
	}
	public void setFacts(List<Fact> facts) {
		this.facts = facts;
	}

	@JsonCreator
	public SecEdgarCompanyFacts(String id, @JsonProperty List<Fact> facts) {
		this.id=id;
		this.facts=facts;
	}
	
	public SecEdgarCompanyFacts() {
		
	}
    
}


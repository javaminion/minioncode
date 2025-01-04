package com.usecase.reportgen.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.usecase.reportgen.document.Fact;

public interface FactRepository extends MongoRepository<Fact, String> {
	
	public List<Fact> findByDocumentRefAndTopic(String documentRef, String topic);
	
}

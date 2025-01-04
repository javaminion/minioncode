package com.usecase.reportgen.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.usecase.reportgen.document.ComputedFact;

public interface ComputedFactRepository extends MongoRepository<ComputedFact, String> {
	
	List<ComputedFact> findByTopic(String topic);
	
	List<ComputedFact> findByCikDocumentRefAndTopic(String cikDocumentRef, String topic);
}

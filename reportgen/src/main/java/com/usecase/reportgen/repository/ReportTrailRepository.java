package com.usecase.reportgen.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.usecase.reportgen.document.ReportTrail;

public interface ReportTrailRepository extends MongoRepository<ReportTrail, String> {
	
	List<ReportTrail> findByDocumentRef(String documentRef);
}

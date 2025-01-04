package com.usecase.reportgen.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.usecase.reportgen.document.ComputedFact;
import com.usecase.reportgen.document.ReportDetail;

public interface ReportDetailRepository extends MongoRepository<ReportDetail, String> {
	
}

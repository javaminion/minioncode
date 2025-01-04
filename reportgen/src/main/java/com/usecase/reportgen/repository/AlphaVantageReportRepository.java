package com.usecase.reportgen.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.usecase.reportgen.document.AlphaVantageReport;

public interface AlphaVantageReportRepository extends MongoRepository<AlphaVantageReport, String> {

}


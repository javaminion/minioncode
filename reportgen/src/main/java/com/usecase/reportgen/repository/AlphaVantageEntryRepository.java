package com.usecase.reportgen.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.usecase.reportgen.document.AlphaVantageEntry;

public interface AlphaVantageEntryRepository extends MongoRepository<AlphaVantageEntry, String> {

}

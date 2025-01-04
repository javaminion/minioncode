package com.usecase.reportgen.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.usecase.reportgen.document.SecEdgarCompanyFacts;

public interface SecEdgarCompanyFactsRepository extends MongoRepository<SecEdgarCompanyFacts, String> {
}


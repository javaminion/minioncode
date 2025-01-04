package com.usecase.reportgen.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.usecase.reportgen.document.ConfigMap;

public interface ConfigMapRepository extends MongoRepository<ConfigMap, String> {
    // Custom query methods (if needed)
	List<ConfigMap> findByStringKey(String key);
}


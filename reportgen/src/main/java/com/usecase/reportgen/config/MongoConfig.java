package com.usecase.reportgen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.usecase.reportgen.repository.FactRepository;
import com.usecase.reportgen.repository.SecEdgarCompanyFactsRepository;

@EnableMongoRepositories(basePackageClasses = {FactRepository.class, SecEdgarCompanyFactsRepository.class})
@Configuration
public class MongoConfig {
	
	@Value("${MONGO_URI}")
	private String mongoUri;
    // Existing configuration
	@Bean 
	public MongoClient mongoClient() 
	{ return MongoClients.create(mongoUri); } 
	
	@Bean 
	public MongoTemplate mongoTemplate(MongoClient mongoClient) { return new MongoTemplate(mongoClient, "somdevdemo");
}
}

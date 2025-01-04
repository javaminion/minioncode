package com.usecase.reportgen.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PythonService {
	
    private static final Logger logger = LoggerFactory.getLogger(PythonService.class);

    private final RestTemplate restTemplate;

    public PythonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchData(String cik, String topic) {
        String baseUrl = "https://us-central1-windy-planet-193902.cloudfunctions.net/secedgarcompanyfacts";
        
        // Build URL with query parameters
        String url = baseUrl + "?cik={cik}&topic={topic}";

        try {
            // Replace placeholders with actual values
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, cik, topic);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed with HTTP status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Error occurred while calling the API: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    
    public String sendPostRequestToSecEdgar(String uniqueId, String cik, List<String> topics) {
    	
    	logger.info("Calling GCP Function to access SEC EDGAR API");
    	
        String baseUrl = "https://us-central1-windy-planet-193902.cloudfunctions.net/secedgarcompanyfacts";
        
        // Prepare the JSON payload
        Map<String, Object> jsonPayload = new HashMap<>();
        jsonPayload.put("cik", cik);
        jsonPayload.put("uniqueId", uniqueId);
        jsonPayload.put("topics", topics);

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create the request entity
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(jsonPayload, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            // Send the POST request
            ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed with HTTP status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while sending POST request: " + e.getMessage());
        }finally {
        	
        }
    }
    
public String sendPostRequestToAlphaVantage(String uniqueId, String symbol, String function, String apiKey, String cutoff, List<String> filter) {
    	
    	logger.info("Calling GCP Function to access Alpha Vantage API");
    	
        String baseUrl = "https://alphavantagedata-827560949543.us-central1.run.app";
        
        // Prepare the JSON payload
        Map<String, Object> jsonPayload = new HashMap<>();
        jsonPayload.put("uniqueId", uniqueId);
        jsonPayload.put("symbol", symbol);
        jsonPayload.put("function", function);
        jsonPayload.put("apikey", apiKey);
        jsonPayload.put("cutoff", cutoff);
        jsonPayload.put("filter", filter);

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create the request entity
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(jsonPayload, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            // Send the POST request
            ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed with HTTP status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while sending POST request: " + e.getMessage());
        }finally {
        	
        }
    }


    
	/*
	 * public String fetchReports(String param) {
	 * 
	 * }
	 */
}


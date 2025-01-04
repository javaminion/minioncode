package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class EDGARService {
	
	private static final Logger logger = LoggerFactory.getLogger(EDGARService.class);
	
	private static final String EDGAR_API_URL = "https://data.sec.gov/submissions/CIK#ID#.json";
	
	private static final String XBRL_API_URL = "https://data.sec.gov/api/xbrl/companyfacts/CIK#ID#.json";

    private final RestTemplate restTemplate;
    
    public EDGARService(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
    }

    public String fetchPublicApiData(String cik) {
	        // Make GET request to the public API
    		String zeroesString = "0";
		
    		for(int loop = 1; loop < 10 - cik.length(); loop++) {
    			zeroesString = zeroesString + "0";
    		}
		
    		String finalParam = zeroesString + cik;
    		
    		String apiUrl = XBRL_API_URL.replace("#ID#", finalParam);
    		
    		logger.info("Initiating Call to URL: " + apiUrl);
    	
    		// Set up headers with User-Agent
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Make the GET request
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class, entity);

            return response.getBody();
    }
    
}

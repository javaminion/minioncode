package com.usecase.reportgen.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usecase.reportgen.document.ConfigMap;
import com.usecase.reportgen.repository.ConfigMapRepository;

import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private ConfigMapRepository repository;

    // Method to get all documents
    public List<ConfigMap> getAllDocuments() {
        return repository.findAll();
    }

    // Method to create a new document
    public ConfigMap createDocument(String stringKey, Map<String, String> jsonValue) {
    	ConfigMap document = new ConfigMap(stringKey, jsonValue);
        return repository.save(document);
    }

    // Method to get a document by ID
    public ConfigMap getDocumentById(String id) {
        return repository.findById(id).orElse(null);
    }

    // Method to update an existing document
    public ConfigMap updateDocument(String id, String stringKey, Map<String, String> jsonValue) {
    	ConfigMap document = repository.findById(id).orElse(null);
        if (document != null) {
            document.setStringKey(stringKey);
            document.setJsonValue(jsonValue);
            return repository.save(document);
        }
        return null;
    }
    
    public ConfigMap fetchDocumentByStringKey(String key) {
    	List<ConfigMap> document = repository.findByStringKey(key);
    	return document.get(0);
    }

    // Method to delete a document by ID
    public void deleteDocument(String id) {
        repository.deleteById(id);
    }
}

package com.usecase.reportgen.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "configmap")
public class ConfigMap {

    @Id
    private String id;

    @Field("stringKey")
    private String stringKey;

    @Field("jsonValue")
    private Map<String, String> jsonValue;

    // Constructors, Getters, and Setters

    public ConfigMap() {
    }

    public ConfigMap(String stringKey, Map<String, String> jsonValue) {
        this.stringKey = stringKey;
        this.jsonValue = jsonValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public Map<String, String> getJsonValue() {
        return jsonValue;
    }

    public void setJsonValue(Map<String, String> jsonValue) {
        this.jsonValue = jsonValue;
    }
}


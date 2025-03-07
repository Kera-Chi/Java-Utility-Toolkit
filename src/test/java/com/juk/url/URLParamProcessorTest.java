package com.juk.url;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class URLParamProcessorTest {

    private URLParamProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new URLParamProcessor();
    }

    @Test
    void parseParams() {
        String paramString = "name=John+Doe&age=25&city=New+York";
        Map<String, String> params = processor.parseParams(paramString);
        
        assertEquals(3, params.size());
        assertEquals("John+Doe", params.get("name"));
        assertEquals("25", params.get("age"));
        assertEquals("New+York", params.get("city"));
    }

    @Test
    void decodeParams() {
        Map<String, String> encodedParams = new LinkedHashMap<>();
        encodedParams.put("name", "John+Doe");
        encodedParams.put("city", "New+York");
        
        Map<String, String> decodedParams = processor.decodeParams(encodedParams);
        
        assertEquals("John Doe", decodedParams.get("name"));
        assertEquals("New York", decodedParams.get("city"));
    }

    @Test
    void encodeParams() {
        Map<String, String> decodedParams = new LinkedHashMap<>();
        decodedParams.put("name", "John Doe");
        decodedParams.put("city", "New York");
        
        Map<String, String> encodedParams = processor.encodeParams(decodedParams);
        
        assertEquals("John+Doe", encodedParams.get("name"));
        assertEquals("New+York", encodedParams.get("city"));
    }

    @Test
    void buildParamString() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("name", "John+Doe");
        params.put("age", "25");
        params.put("city", "New+York");
        
        String paramString = processor.buildParamString(params);
        
        assertEquals("name=John+Doe&age=25&city=New+York", paramString);
    }

    @Test
    void jsonConversion() throws IOException {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("name", "John Doe");
        params.put("age", "25");
        
        String json = processor.toFormattedJson(params);
        Map<String, String> parsedParams = processor.fromJson(json);
        
        assertEquals(params.size(), parsedParams.size());
        assertEquals(params.get("name"), parsedParams.get("name"));
        assertEquals(params.get("age"), parsedParams.get("age"));
    }
} 
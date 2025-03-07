package com.juk.api;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juk.url.URLParamProcessor;

/**
 * REST API Controller for URL parameter processing utility
 */
@RestController
@RequestMapping("/api/url-params")
public class URLParamController {

    private final URLParamProcessor processor;
    private final ObjectMapper objectMapper;

    /**
     * Creates a new URL parameter controller instance
     * Initializes processor and object mapper
     */
    public URLParamController() {
        this.processor = new URLParamProcessor();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Parses URL parameter string
     * 
     * @param paramString URL parameter string
     * @return Map of parsed parameters
     */
    @GetMapping("/parse")
    public ResponseEntity<?> parseParams(@RequestParam String paramString) {
        try {
            Map<String, String> params = processor.parseParams(paramString);
            Map<String, String> decodedParams = processor.decodeParams(params);
            return ResponseEntity.ok(decodedParams);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Parsing parameters failed: " + e.getMessage());
        }
    }

    /**
     * Converts JSON parameters to URL parameter string
     * 
     * @param params Parameter Map
     * @return URL parameter string
     */
    @PostMapping("/build")
    public ResponseEntity<?> buildParamString(@RequestBody Map<String, String> params) {
        try {
            Map<String, String> encodedParams = processor.encodeParams(params);
            String paramString = processor.buildParamString(encodedParams);
            return ResponseEntity.ok(paramString);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Building parameter string failed: " + e.getMessage());
        }
    }

    /**
     * Encodes URL parameters
     * 
     * @param params Decoded parameters
     * @return Encoded parameters
     */
    @PostMapping("/encode")
    public ResponseEntity<?> encodeParams(@RequestBody Map<String, String> params) {
        try {
            Map<String, String> encodedParams = processor.encodeParams(params);
            return ResponseEntity.ok(encodedParams);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Encoding parameters failed: " + e.getMessage());
        }
    }

    /**
     * Decodes URL parameters
     * 
     * @param params Encoded parameters
     * @return Decoded parameters
     */
    @PostMapping("/decode")
    public ResponseEntity<?> decodeParams(@RequestBody Map<String, String> params) {
        try {
            Map<String, String> decodedParams = processor.decodeParams(params);
            return ResponseEntity.ok(decodedParams);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Decoding parameters failed: " + e.getMessage());
        }
    }

    /**
     * Converts URL parameter string to JSON, which can be modified and then
     * converted back
     * 
     * @param reqParams Request body containing paramString field
     * @return Processing results
     */
    @PostMapping("/process")
    public ResponseEntity<?> processUrlParams(@RequestBody Map<String, Object> reqParams) {
        try {
            // Get and check paramString
            Object paramStringObj = reqParams.get("paramString");
            if (paramStringObj == null) {
                return ResponseEntity.badRequest().body("Please provide paramString field");
            }

            String paramString = paramStringObj.toString();

            Map<String, String> params = processor.parseParams(paramString);
            Map<String, String> decodedParams = processor.decodeParams(params);

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("originalParamString", paramString);
            response.put("parsedParams", decodedParams);

            // If modifiedParams is provided, process conversion back to URL parameter
            // string
            if (reqParams.containsKey("modifiedParams")) {
                try {
                    Object modifiedParamsObj = reqParams.get("modifiedParams");
                    Map<String, String> modifiedParams;

                    // Check if modifiedParams is of Map type
                    if (modifiedParamsObj instanceof Map) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> tempMap = (Map<String, Object>) modifiedParamsObj;
                        modifiedParams = new LinkedHashMap<>();

                        // Convert all values to strings
                        for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
                            modifiedParams.put(entry.getKey(),
                                    entry.getValue() != null ? entry.getValue().toString() : "");
                        }
                    } else if (modifiedParamsObj instanceof String) {
                        // If it's a string, try to parse as JSON
                        try {
                            @SuppressWarnings("unchecked")
                            Map<String, String> parsedMap = objectMapper.readValue(
                                    modifiedParamsObj.toString(), LinkedHashMap.class);
                            modifiedParams = parsedMap;
                        } catch (IOException e) {
                            return ResponseEntity.badRequest().body(
                                    "Unable to parse modifiedParams: " + e.getMessage());
                        }
                    } else {
                        return ResponseEntity.badRequest().body(
                                "modifiedParams format incorrect, should be JSON object");
                    }

                    Map<String, String> encodedModifiedParams = processor.encodeParams(modifiedParams);
                    String newParamString = processor.buildParamString(encodedModifiedParams);
                    response.put("newParamString", newParamString);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(
                            "Processing modifiedParams failed: " + e.getMessage());
                }
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Processing parameters failed: " + e.getMessage());
        }
    }
}
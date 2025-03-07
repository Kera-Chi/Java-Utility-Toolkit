package com.juk.url;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * URL Parameter Processing Utility
 * Supports parsing, encoding/decoding, JSON formatting, and conversion of URL
 * parameters
 */
public class URLParamProcessor {

    private final ObjectMapper objectMapper;

    /**
     * Creates a new URL parameter processor instance
     * Initializes JSON object mapper with formatted output enabled
     */
    public URLParamProcessor() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Parses URL parameter string into a Map
     *
     * @param paramString URL parameter string, e.g. "name=John&amp;age=25"
     * @return Map containing the parameters
     */
    public Map<String, String> parseParams(String paramString) {
        Map<String, String> params = new LinkedHashMap<>();
        if (paramString == null || paramString.isEmpty()) {
            return params;
        }

        String[] pairs = paramString.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = idx > 0 ? pair.substring(0, idx) : pair;
            String value = idx > 0 && pair.length() > idx + 1 ? pair.substring(idx + 1) : "";
            params.put(key, value);
        }
        return params;
    }

    /**
     * Decodes URL parameters
     *
     * @param encodedParams Map of encoded parameters
     * @return Map of decoded parameters
     */
    public Map<String, String> decodeParams(Map<String, String> encodedParams) {
        Map<String, String> decodedParams = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : encodedParams.entrySet()) {
            String key = decodeValue(entry.getKey());
            String value = decodeValue(entry.getValue());
            decodedParams.put(key, value);
        }
        return decodedParams;
    }

    /**
     * Encodes URL parameters
     *
     * @param decodedParams Map of decoded parameters
     * @return Map of encoded parameters
     */
    public Map<String, String> encodeParams(Map<String, String> decodedParams) {
        Map<String, String> encodedParams = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : decodedParams.entrySet()) {
            String key = encodeValue(entry.getKey());
            String value = encodeValue(entry.getValue());
            encodedParams.put(key, value);
        }
        return encodedParams;
    }

    /**
     * Converts parameter Map to URL parameter string
     *
     * @param params Parameter Map
     * @return URL parameter string
     */
    public String buildParamString(Map<String, String> params) {
        if (params.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString();
    }

    /**
     * Converts parameters to formatted JSON
     *
     * @param params Parameter Map
     * @return Formatted JSON string
     * @throws IOException if JSON conversion fails
     */
    public String toFormattedJson(Map<String, String> params) throws IOException {
        return objectMapper.writeValueAsString(params);
    }

    /**
     * Parses JSON string into parameter Map
     *
     * @param json JSON string
     * @return Parameter Map
     * @throws IOException if JSON parsing fails
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> fromJson(String json) throws IOException {
        return objectMapper.readValue(json, LinkedHashMap.class);
    }

    /**
     * Decodes a single URL value
     */
    private String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            return value;
        }
    }

    /**
     * Encodes a single URL value
     */
    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            return value;
        }
    }
}
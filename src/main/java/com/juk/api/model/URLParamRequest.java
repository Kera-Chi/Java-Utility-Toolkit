package com.juk.api.model;

import java.util.Map;
import lombok.Data;

/**
 * URL Parameter Request Model
 * Used for receiving and processing URL parameter API requests
 */
@Data
public class URLParamRequest {
    private String paramString;
    private Map<String, String> modifiedParams;
}
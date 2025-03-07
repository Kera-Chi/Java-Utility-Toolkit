package com.juk.api.model;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * URL Parameter Response Model
 * Used for structuring API responses
 */
@Data
@Builder
public class URLParamResponse {
      private String originalParamString;
      private Map<String, String> parsedParams;
      private String newParamString;
      private String error;
}
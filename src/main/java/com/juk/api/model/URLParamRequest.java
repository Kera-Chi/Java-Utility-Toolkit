package com.juk.api.model;

import java.util.Map;

/**
 * URL Parameter Request Model
 * Used for receiving and processing URL parameter API requests
 */
public class URLParamRequest {
    private String paramString;
    private Map<String, String> modifiedParams;

    /**
     * Gets the URL parameter string
     * 
     * @return URL parameter string
     */
    public String getParamString() {
        return paramString;
    }

    /**
     * Sets the URL parameter string
     * 
     * @param paramString URL parameter string to set
     */
    public void setParamString(String paramString) {
        this.paramString = paramString;
    }

    /**
     * Gets the modified parameters map
     * 
     * @return Map of parameter names and values
     */
    public Map<String, String> getModifiedParams() {
        return modifiedParams;
    }

    /**
     * Sets the modified parameters map
     * 
     * @param modifiedParams Map of parameter names and values
     */
    public void setModifiedParams(Map<String, String> modifiedParams) {
        this.modifiedParams = modifiedParams;
    }
}
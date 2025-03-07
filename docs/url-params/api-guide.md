# URL Parameters API Guide

This document provides detailed information about the URL Parameters API endpoints.

## API Endpoints

### Parse URL Parameters

Converts a URL parameter string into a map of key-value pairs.

- **Endpoint**: `/api/url-params/parse`
- **Method**: GET
- **Parameters**:
  - `paramString`: The URL parameter string to parse
- **Response**: JSON object containing decoded parameters

### Build URL Parameter String

Creates a URL parameter string from a map of key-value pairs.

- **Endpoint**: `/api/url-params/build`
- **Method**: POST
- **Request Body**: JSON object with parameter key-value pairs
- **Response**: URL parameter string

### Encode Parameters

URL encodes parameter keys and values.

- **Endpoint**: `/api/url-params/encode`
- **Method**: POST
- **Request Body**: JSON object with unencoded parameter key-value pairs
- **Response**: JSON object with encoded parameters

### Decode Parameters

URL decodes parameter keys and values.

- **Endpoint**: `/api/url-params/decode`
- **Method**: POST
- **Request Body**: JSON object with encoded parameter key-value pairs
- **Response**: JSON object with decoded parameters

### Process URL Parameters

Advanced endpoint for parsing, viewing, modifying, and rebuilding URL parameters.

- **Endpoint**: `/api/url-params/process`
- **Method**: POST
- **Request Body**:
  - `paramString`: The URL parameter string to parse
  - `modifiedParams` (optional): Modified parameters to convert back to a URL string
- **Response**: JSON object containing:
  - `originalParamString`: The original parameter string
  - `parsedParams`: The decoded parameters
  - `newParamString` (if modifiedParams provided): New parameter string with modifications
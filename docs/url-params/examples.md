# URL Parameters Usage Examples

This document provides practical examples of how to use the URL Parameters API.

## Parsing URL Parameters

Convert a URL parameter string into a key-value map.

### Request

```bash
curl -X GET "http://localhost:8080/api/url-params/parse?paramString=name%3DJohn%20Doe%26age%3D30%26role%3Dadmin"
```

### Response

```json
{
  "name": "John Doe",
  "age": "30",
  "role": "admin"
}
```

## Building URL Parameter String

Create a URL parameter string from a key-value map.

### Request

```bash
curl -X POST "http://localhost:8080/api/url-params/build" \
-H "Content-Type: application/json" \
-d '{
  "name": "John Doe",
  "age": "30",
  "role": "admin"
}'
```

### Response

```json
{
  "paramString": "name=John%20Doe&age=30&role=admin"
}
```

## Encoding URL Parameters
URL encode parameter keys and values.
### Request

```bash
curl -X POST "http://localhost:8080/api/url-params/encode" \
-H "Content-Type: application/json" \
-d '{
  "name": "John Doe",
  "age": "30",
  "role": "admin"
}'
```
### Response

```json
{
  "encodedParams": {
    "name": "John%20Doe",
    "age": "30",
    "role": "admin"
  }
}
```
## Decoding URL Parameters
URL decode parameter keys and values.
### Request

```bash
curl -X POST "http://localhost:8080/api/url-params/decode" \
-H "Content-Type: application/json" \
-d '{
  "name": "John%20Doe",
  "age": "30",
  "role": "admin"
}'
```
### Response

```json
{
  "decodedParams": {
    "name": "John Doe",
    "age": "30",
    "role": "admin"
  }
}
```

## Processing URL Parameters
Advanced endpoint for parsing, viewing, modifying, and rebuilding URL parameters.
### Request (Parse Only)
```bash
curl -X POST "http://localhost:8080/api/url-params/process" \
-H "Content-Type: application/json" \
-d '{
  "paramString": "name=John%20Doe&age=30&role=admin"
}'
```

### Response

```json
{
  "originalParamString": "name=John%20Doe&age=30&role=admin",
  "parsedParams": {
    "name": "John Doe",
    "age": "30",
    "role": "admin"
  }
}
```
### Request (Parse and Modify)
```bash
curl -X POST "http://localhost:8080/api/url-params/process" \
-H "Content-Type: application/json" \
-d '{
  "paramString": "name=John%20Doe&age=30&role=admin",
  "modifiedParams": {
    "age": "31",
    "role": "user"
  }
}'
```
### Response

```json
{
  "originalParamString": "name=John%20Doe&age=30&role=admin",
  "parsedParams": {
    "name": "John Doe",
    "age": "31",
    "role": "user"
  },
  "newParamString": "name=John%20Doe&age=31&role=user"
}
```
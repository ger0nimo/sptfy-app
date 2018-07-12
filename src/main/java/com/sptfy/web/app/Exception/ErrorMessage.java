package com.sptfy.web.app.Exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL) // ignore field if its value is null and it's not included in the JSON response
public class ErrorMessage {

    @JsonProperty("Status Code")
    private Integer statusCode;

    @JsonProperty("Status")
    private HttpStatus status;

    @JsonProperty("Message")
    private String message;

    public ErrorMessage(Integer statusCode, HttpStatus status, String message) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
    }
}

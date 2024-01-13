package com.assignment.lead.model;

import java.util.List;

public class ErrorResponse {
    private String status;


    private ErrorResponseDetails errorResponse;

    // Constructors, getters, and setters

    // Default constructor
    public ErrorResponse() {
    }

    // Parameterized constructor
    public ErrorResponse(String status, ErrorResponseDetails errorResponse) {
        this.status = status;
        this.errorResponse = errorResponse;
    }

    // Getter and Setter methods
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorResponseDetails getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponseDetails errorResponse) {
        this.errorResponse = errorResponse;
    }

    // Inner class representing the details of the error response
    public static class ErrorResponseDetails {
        private String code;
        private List<String> messages;

        // Constructors, getters, and setters

        // Default constructor
        public ErrorResponseDetails() {
        }

        // Parameterized constructor
        public ErrorResponseDetails(String code, List<String> messages) {
            this.code = code;
            this.messages = messages;
        }

        // Getter and Setter methods
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<String> getMessages() {
            return messages;
        }

        public void setMessages(List<String> messages) {
            this.messages = messages;
        }
    }
}

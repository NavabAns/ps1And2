package com.assignment.lead.model;

public class SuccessResponse {
    public String data;
    public String status;

    public SuccessResponse() {
    }

    public SuccessResponse(String status, String data) {
        this.status = status;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

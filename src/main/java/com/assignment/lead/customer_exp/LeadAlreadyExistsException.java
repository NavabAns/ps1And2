package com.assignment.lead.customer_exp;

public class LeadAlreadyExistsException extends RuntimeException {
    public LeadAlreadyExistsException(String message) {
        super(message);
    }
}

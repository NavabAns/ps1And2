package com.assignment.lead.controller_advise;


import com.assignment.lead.constant.SuccessAndErrorCode;
import com.assignment.lead.customer_exp.LeadAlreadyExistsException;
import com.assignment.lead.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        // Get all errors
        List<String> exceptionalErrors
                = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse.ErrorResponseDetails details = new ErrorResponse.ErrorResponseDetails(SuccessAndErrorCode.ErrorCodeAndMessage.VALIDATION_ERROR_CODE,
                exceptionalErrors);
        ErrorResponse errorResponse = new ErrorResponse(SuccessAndErrorCode.ErrorCodeAndMessage.VALIDATION_ERROR, details);
        return new ResponseEntity<>(errorResponse, status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(LeadAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleLeadAlreadyExistsException(LeadAlreadyExistsException ex) {
        ErrorResponse.ErrorResponseDetails details = new ErrorResponse.ErrorResponseDetails(SuccessAndErrorCode.ErrorCodeAndMessage.LEAD_ALREADY_EXIST_CODE,
                Arrays.asList(ex.getMessage()));
        ErrorResponse errorResponse = new ErrorResponse(SuccessAndErrorCode.ErrorCodeAndMessage.ERROR, details);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
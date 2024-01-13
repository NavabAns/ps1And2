package com.assignment.lead.controller;

import com.assignment.lead.constant.SuccessAndErrorCode;
import com.assignment.lead.entity.LeadEntity;
import com.assignment.lead.model.ErrorResponse;
import com.assignment.lead.model.FetchLeads;
import com.assignment.lead.model.SuccessResponse;
import com.assignment.lead.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/leads")
@Validated
public class LeadController {
    @Autowired
    private LeadService leadService;


    @PostMapping(value = "/generate-leads")
    public ResponseEntity<SuccessResponse> createLead(@RequestBody @Valid LeadEntity lead) {

        LeadEntity lead1 = leadService.createLead(lead);
        SuccessResponse response = new SuccessResponse(
                 SuccessAndErrorCode.SuccessCodeAndMessage.SUCCESS,SuccessAndErrorCode.SuccessCodeAndMessage.CREATED_LEAD);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/byMobileNumber")
    public ResponseEntity<?> getLeadsByMobileNumber(@RequestParam(value = "mobileNumber") String mobileNumber) {
        List<LeadEntity> leads = leadService.getLeadsByMobileNumber(mobileNumber);

        if (leads.isEmpty()) {
            ErrorResponse.ErrorResponseDetails details = new ErrorResponse.ErrorResponseDetails(SuccessAndErrorCode.ErrorCodeAndMessage.NO_LEAD_FND,
                    Arrays.asList(SuccessAndErrorCode.ErrorCodeAndMessage.NO_LEAD_FND_MSG));
            ErrorResponse errorResponse = new ErrorResponse(SuccessAndErrorCode.ErrorCodeAndMessage.ERROR, details);
            return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
        }
       return new ResponseEntity<>(new FetchLeads(SuccessAndErrorCode.SuccessCodeAndMessage.SUCCESS, leads),HttpStatus.OK);
    }

}
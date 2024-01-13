package com.assignment.lead.service;

import com.assignment.lead.constant.SuccessAndErrorCode;
import com.assignment.lead.customer_exp.LeadAlreadyExistsException;
import com.assignment.lead.entity.LeadEntity;
import com.assignment.lead.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LeadService {
     LeadEntity createLead(LeadEntity lead);
     List<LeadEntity> getLeadsByMobileNumber(String mobileNumber);
}
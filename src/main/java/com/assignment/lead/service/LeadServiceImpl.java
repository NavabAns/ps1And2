package com.assignment.lead.service;

import com.assignment.lead.constant.SuccessAndErrorCode;
import com.assignment.lead.customer_exp.LeadAlreadyExistsException;
import com.assignment.lead.entity.LeadEntity;
import com.assignment.lead.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService{
    @Autowired
    private LeadRepository leadRepository;

    public LeadEntity createLead(LeadEntity lead) {
        if (leadRepository.existsByLeadId(lead.getLeadId())) {
            throw new LeadAlreadyExistsException(SuccessAndErrorCode.SuccessCodeAndMessage.LEAD_EXIST_MSG);
        }

        // Additional business logic or validation if needed

        return leadRepository.save(lead);
    }

    @Override
    public List<LeadEntity> getLeadsByMobileNumber(String mobileNumber) {
        return leadRepository.findByMobileNumber(mobileNumber);
    }
}
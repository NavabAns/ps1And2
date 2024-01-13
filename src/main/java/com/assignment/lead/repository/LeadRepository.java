package com.assignment.lead.repository;

import com.assignment.lead.entity.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeadRepository extends JpaRepository<LeadEntity, Integer> {
    boolean existsByLeadId(Integer leadId);
    List<LeadEntity> findByMobileNumber( String mobileNumber);
}
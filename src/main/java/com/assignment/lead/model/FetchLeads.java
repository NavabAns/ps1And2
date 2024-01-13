package com.assignment.lead.model;

import com.assignment.lead.entity.LeadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchLeads implements Serializable {
    private String status;
    private List<LeadEntity> data;

    // Constructors, getters, and setters
}
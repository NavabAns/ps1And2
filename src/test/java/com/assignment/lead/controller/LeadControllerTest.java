package com.assignment.lead.controller;

import com.assignment.lead.constant.SuccessAndErrorCode;
import com.assignment.lead.customer_exp.LeadAlreadyExistsException;
import com.assignment.lead.entity.LeadEntity;
import com.assignment.lead.service.LeadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static com.assignment.lead.constant.SuccessAndErrorCode.SuccessCodeAndMessage.LEAD_EXIST_MSG;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LeadController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LeadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeadService leadService;

    @Test
    @Order(1)
    void testCreateLead_Success() throws Exception {
        // Arrange
        LeadEntity lead = createSampleLead();
        when(leadService.createLead(any(LeadEntity.class))).thenReturn(lead);

        // Act and Assert
        mockMvc.perform(post("/api/leads/generate-leads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(lead)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("success")))
                .andExpect(jsonPath("$.data", is(SuccessAndErrorCode.SuccessCodeAndMessage.CREATED_LEAD)));

        verify(leadService, times(1)).createLead(any(LeadEntity.class));
    }

    @Test
    @Order(2)
    void testCreateLead_AlreadyExists() throws Exception {
        // Arrange
        LeadEntity lead = createSampleLead();
        when(leadService.createLead(any(LeadEntity.class))).thenThrow(new LeadAlreadyExistsException(LEAD_EXIST_MSG));

        // Act and Assert
        mockMvc.perform(post("/api/leads/generate-leads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(lead)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("error")))
                .andExpect(jsonPath("$.errorResponse.code", is("E10010")))
                .andExpect(jsonPath("$.errorResponse.messages[0]", is(LEAD_EXIST_MSG)));

        verify(leadService, times(1)).createLead(any(LeadEntity.class));
    }

    @Test
    @Order(3)
    public void testGetLeadsByMobileNumber_WithLeads() throws Exception {
        // Mock data
        String mobileNumber = "8877887788";
        LeadEntity lead1 = createSampleLead();
        List<LeadEntity> leads = Arrays.asList(lead1);

        // Mock the service response
        when(leadService.getLeadsByMobileNumber(anyString())).thenReturn(leads);

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/leads/byMobileNumber")
                        .param("mobileNumber", mobileNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].leadId").value("5678"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].firstName").value("Vineet"));
        // Add more assertions based on your entity structure
    }

    @Test
    @Order(4)
    public void testGetLeadsByMobileNumber_WithNoLeads() throws Exception {
        // Mock data
        String mobileNumber = "8877887788";

        // Mock the service response with an empty list
        when(leadService.getLeadsByMobileNumber(anyString())).thenReturn(Arrays.asList());

        // Perform the request and assert the response for no leads
        mockMvc.perform(MockMvcRequestBuilders.get("/api/leads/byMobileNumber")
                        .param("mobileNumber", mobileNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("error"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorResponse.code").value("E10011"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorResponse.messages[0]").value(SuccessAndErrorCode.ErrorCodeAndMessage.NO_LEAD_FND_MSG));
    }

    private LeadEntity createSampleLead() throws ParseException {
        LeadEntity lead = new LeadEntity();
        lead.setLeadId(5678);
        lead.setFirstName("Vineet");
        lead.setLastName("KV");
        lead.setMobileNumber("8877887788");
        lead.setGender("Male");
        String date = "27/01/2022";
        LocalDate parse = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        lead.setDOB(parse);
        lead.setEmail("v@gmail.com");
        return lead;
    }
    @Autowired
    private ObjectMapper objectMapper;
    private  String asJsonString(final Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}

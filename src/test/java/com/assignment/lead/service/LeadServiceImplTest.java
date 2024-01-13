package com.assignment.lead.service;

import com.assignment.lead.customer_exp.LeadAlreadyExistsException;
import com.assignment.lead.entity.LeadEntity;
import com.assignment.lead.repository.LeadRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LeadServiceImplTest {

    @Mock
    private LeadRepository leadRepository;

    @InjectMocks
    private LeadServiceImpl leadService;

    @Test
    @Order(1)
    void testCreateLead_Success() throws ParseException {
        // Arrange
        LeadEntity lead = createSampleLead();
        when(leadRepository.existsByLeadId(anyInt())).thenReturn(false);
        when(leadRepository.save(any(LeadEntity.class))).thenReturn(lead);

        // Act
        LeadEntity createdLead = leadService.createLead(lead);

        // Assert
        assertNotNull(createdLead);
        assertEquals(lead.getLeadId(), createdLead.getLeadId());
        verify(leadRepository, times(1)).existsByLeadId(anyInt());
        verify(leadRepository, times(1)).save(any(LeadEntity.class));
    }

    @Test
    @Order(2)
    void testCreateLead_AlreadyExists() throws ParseException {
        // Arrange
        LeadEntity lead = createSampleLead();
        when(leadRepository.existsByLeadId(anyInt())).thenReturn(true);

        // Act and Assert (exception is expected)
        assertThrows(LeadAlreadyExistsException.class, () -> leadService.createLead(lead));
    }
    @Test
    @Order(3)
    public void testGetLeadsByMobileNumber() throws ParseException {
        // Mock data
        String mobileNumber = "8877887788";
        LeadEntity lead1 = createSampleLead();lead1.setLeadId(123);
        LeadEntity lead2 = createSampleLead();lead2.setLeadId(345);
        List<LeadEntity> expectedLeads = Arrays.asList(lead1, lead2);

        // Mock the repository response
        when(leadRepository.findByMobileNumber(mobileNumber)).thenReturn(expectedLeads);

        // Call the service method
        List<LeadEntity> actualLeads = leadService.getLeadsByMobileNumber(mobileNumber);

        // Verify the results
        assertEquals(expectedLeads.size(), actualLeads.size());
        assertEquals(expectedLeads.get(0).getLeadId(), actualLeads.get(0).getLeadId());
        assertEquals(expectedLeads.get(1).getLeadId(), actualLeads.get(1).getLeadId());
        // Add more assertions based on your entity structure
    }

    private LeadEntity createSampleLead() throws ParseException {
        LeadEntity lead = new LeadEntity();
        lead.setLeadId(5678);
        lead.setFirstName("Vineet");
        lead.setLastName("KV");
        lead.setMobileNumber("8877887788");
        lead.setGender("Male");
        String dateString = "01/01/2022";
        LocalDate parse = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        lead.setDOB(parse);
        lead.setEmail("v@gmail.com");
        return lead;
    }
}
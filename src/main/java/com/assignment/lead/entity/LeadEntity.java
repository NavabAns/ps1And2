package com.assignment.lead.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "lead")
public class LeadEntity {
    @Id
    @NotNull(message = "LeadId is mandatory")
    @Min(value = 1, message = "LeadId should be greater than or equal to 1")
    @Max(value = Integer.MAX_VALUE, message = "LeadId should be less than or equal to " + Integer.MAX_VALUE)
    private Integer leadId;

    @NotBlank(message = "FirstName is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "FirstName should contain only alphabets")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "MiddleName should contain only alphabets")
    private String middleName;

    @NotBlank(message = "LastName is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "LastName should contain only alphabets")
    private String lastName;

    @NotNull(message = "MobileNumber is mandatory")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid MobileNumber format")
    private String mobileNumber;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "^(Male|Female|Others)$", message = "Invalid Gender")
    private String Gender;

    @NotNull(message = "DOB is mandatory")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate DOB;

    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @JsonProperty("Gender")
    public String getGender() {
        return Gender;
    }

    @JsonProperty("Gender")
    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    @JsonProperty("DOB")
    public LocalDate getDOB() {
        return DOB;
    }

    @JsonProperty("DOB")
    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Email(message = "Invalid email format",regexp = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$")
    private String email;

    public LeadEntity() {
    }


}
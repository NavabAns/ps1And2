package com.assignment.lead.constant;

public interface SuccessAndErrorCode {
     interface ErrorCodeAndMessage{
      String LEAD_ALREADY_EXIST_CODE = "E10010";
      String VALIDATION_ERROR_CODE = "V8888";
      String ERROR = "error";

      String NO_LEAD_FND = "E10011";

      String VALIDATION_ERROR = "validation error";

      String NO_LEAD_FND_MSG = "No Lead found with the Mobile Number ";
    }
    interface SuccessCodeAndMessage{
         String SUCCESS = "success";
         String CREATED_LEAD = "Created Leads Successfully";
         String LEAD_EXIST_MSG = "Lead Already Exists in the database with the lead id";
    }
}

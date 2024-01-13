                     @Author Ansari Mohamad Navabyasin Shamim
This project is buid on Spring Boot 3.x.x, JAVA 17, Gradle 8.5
Name of the project is LeadManagement Syste.
  This project has some basic functionality like generate lead, fetch lead
    Generate Lead CURL 
      curl --location 'http://localhost:8081/api/leads/generate-leads' \
             --header 'Content-Type: application/json' \
             --data-raw '{
               "leadId": "596797",
               "firstName": "Vineet",
               "lastName": "KV",
               "mobileNumber": "8877887788",
               "Gender": "Male",
               "DOB": "01/01/2012",
               "email": "v@gmail.com"
              }'
      all the keys in above curl is mandatory except middleName.
      Response of above curl:
        {
         "data": "Created Leads Successfully",
         "status": "success"
       }

      if db already have the data with same leadId it will not generate any lead and send error response
        {
            "status": "error",
            "errorResponse": {
                  "code": "E10010",
                  "messages": [
                     "Lead Already Exists in the database with the lead id"
                     ]
                  }
         }

Fech Leads Api is GET Api here we are taking mobileNumber in request parameter
  it will fetch all the lead data belonging to the mobile number
  Fetch lead curl
    curl --location 'http://localhost:8081/api/leads/byMobileNumber?mobileNumber=8877887788'
  response of above curl is
      {
         "status": "success",
       "data": [
               {
                   "leadId": 596797,
                  "firstName": "Vineet",
                  "middleName": null,
                  "lastName": "KV",
                 "mobileNumber": "8877887788",
                 "DOB": "01/01/2012",
                 "email": "v@gmail.com",
                 "Gender": "Male"
             }
        ]
  }

    if no data found then it will give the error response
       {
    "status": "error",
    "errorResponse": {
        "code": "E10011",
        "messages": [
            "No Lead found with the Mobile Number "
            ]
        }
     }

I this application has spring validator which do the validation of entity.
 {
    "status": "validation error",
    "errorResponse": {
        "code": "V8888",
        "messages": [
            "LastName is mandatory",
            "LastName should contain only alphabets"
        ]
    }
}

{
    "status": "validation error",
    "errorResponse": {
        "code": "V8888",
        "messages": [
            "Invalid email format"
        ]
    }
}




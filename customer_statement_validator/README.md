
##Customer Statement Proccessor
	Customer statement record needs to be validated using Java 8 and Spring Boot
	
##Getting Started
	Rabobank receives monthly deliveries of customer statement records. This information is delivered in JSON Format. These records need to be validated.
    Implemented a REST service which receives the customer statement JSON as a POST data. Below validations needs to be done:
    1. All transaction references should be unique 
    2. The end balance needs to be validated ( Start Balance +/- Mutation = End Balance ) 

##Technologies
    Java 8
    Spring Boot
    Maven

##Assumptions
    1. Assumed empty json request body as an exception and handled it with RecordNotFoundException 
        and shown response result as RECORD_NOT_FOUND with empty errrorList 
    2. For every json parser exception handled as HttpMessageNotReadable exception with status HttpStatus.Bad_REQUEST 
    3. For other situation handled as HttpStatus.INTERNAL_SERVER_ERROR

##Running the test
    mvn clean test or
    Right click test file in intellij or eclipse and select Run
##Running the Application
    type the command inside the project folder as below:
        mvn spring-boot:run

   
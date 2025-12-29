# CSV WRITE

## Problem Statement
- When the user has to enter many rows of data into the system, it becomes overwhelming.
- To avoid manual entry, the user can upload a CSV file to insert data in bulk.

## Security
- Used JWT to authenticate the user
- Used Spring Security

## Work Flow

### Register
- User enters name and password

### Login
- When the user enters the correct name and password, a JWT token is sent in the response

### File Upload
- User uploads the CSV file with data
- The system reads the CSV file and saves the data to the database

## Test Cases
- Written 8 test cases to verify the service layer functionality

## SonarQube
- Used SonarQube to scan the project and analyze code quality
[Sonar Qube] (https://demo.fingress.com/analyze/dashboard?id=csvwrite)
## Swagger
- Created Swagger documentation for the API endpoints
[Swagger] (https://app.swaggerhub.com/apis/bluescope-444/csvwrite/1.0.0)
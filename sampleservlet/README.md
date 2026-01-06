# Offer upload

    This API is for Loan Management in Bank Site and Customers Site. Now the 1st phase of offer upload have been completed. which contains an total of six endpoints for each business needs. Two are for the Bank employee to register and login. and Four are for the offer loan upload by the bank administratives.

---

## Security

- JWT is used to authenticate users
- APIs are secured to ensure only authorized users can upload or edit data
- Token-based access control is enforced for all protected endpoints

## Workflow
### Register

- User registers using valid credentials 
- Credentials are securely stored using password hashing

### Login

- User logs in with valid credentials
- On successful authentication, a JWT token is generated and returned in the response
- This token must be included in all subsequent API requests

### Data Upload 

- User sends bulk data to the system using a secure API endpoint
- Data is sent in JSON format
- The system validates the request and processes multiple records in a single API call
- Valid data is saved to the database

### Data Edit / Update 

- User updates existing records using a dedicated edit/update API
- Only authorized users with a valid JWT token can modify data
- The system validates the input and updates the corresponding records in the database 


# Loan Management System

A Spring Boot-based Loan Management System with REST and SOAP API integrations.

## Features
- Subscription API
- Loan Request API
- Loan Status API
- SOAP integration for KYC and Transactions
- Scoring Engine integration with retries
- Swagger API documentation

## Installation
```sh
mvn clean package
java -jar target/loan-management-system.jar
```

## Docker Deployment
```sh
docker build -t loan-management-system .
docker run -p 8080:8080 loan-management-system
```

## API Docs
After running, visit: [Swagger UI](http://localhost:8080/swagger-ui.html)

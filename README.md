# Mobile Number API - Vodafone

A REST Web-Service responsible of maintaining a database of mobile numbers.

## Endpoints

### API
`http://localhost:8080/subscribers`

### Database
`http://localhost:8080/h2-console`

# Used Technologies

- **Eclipse Neon** - 4.6.3
- **OPEN JDK** - 10.0.2
- **Spring-Boot** - 2.0.3
- **Maven** - 3.5.4
- **H2 in-memory database** 

### Things to Think Before Going in Production

- Security: OATH 2.0 or JWT
- Persistent Database: MySQL or PostgreSQL
- Cloud x On-Premise
- API Gateway: Rate limit, Payload Size, SQL Injection, etc.

# Properties 

`application-<environment>.properties`

This file contains properties for configuring database and logging. There are two files under the src/main/resources directory:

- application-dev.properties
- application-prod.properties

You should set at least propertie 'logging.path' for defining file path:

# Execution

To run the Web-Service, follow the steps below according to your operation system:

** This assumes that Maven and Java is already configured properly in your environment **

## Windows
1. Open the  **Command Prompt**
2. Navigate to the project root folder: `cd C:\<your_project_path_folder>`
3. Run command `mvn clean package`
4. Navigate to target folder: `cd target`
5. Move the .jar file `mobile-number-api-<version>.jar` to your desired environment folder
6. In your environment folder run command:
- for production environment:
`start java -Dspring.profiles.active=prod -jar mobile-number-api-<version>.jar`
- for development environment:
`start java -Dspring.profiles.active=dev -jar mobile-number-api-<version>.jar`


## Linux
1. Open the  **Console**
2. Navigate to the project root folder: `cd /<your_project_path_folder>`
3. Run command `mvn clean package`
4. Navigate to target folder: `cd target`
5. Move the .jar file`mobile-number-api-<version>.jar` to your desired environment folder
6. In your environment folder run command:
- for production environment:
`java -Dspring.profiles.active=prod -jar mobile-number-api-<version>.jar &`
- for development environment:
`java -Dspring.profiles.active=dev -jar mobile-number-api-<version>.jar &`

# Basic requests examples

Using `curl` command tool

## List
curl -X GET http://localhost:8080/subscribers

## Get
curl -X GET http://localhost:8080/subscribers/1

## Create
curl -X POST -H "Content-Type: application/json" -d "{ \"msisdn\": \"1155972532645\", \"customerIdOwner\": 0, \"customerIdUser\": 0,  \"serviceType\": \"MOBILE_PREPAID\", \"serviceStartDate\": 1528208058559 }" http://localhost:8080/subscribers

curl -X POST -H "Content-Type: application/json" -d "{ \"msisdn\": \"1155974786091\", \"customerIdOwner\": 1, \"customerIdUser\": 1,  \"serviceType\": \"MOBILE_POSTPAID\", \"serviceStartDate\": 1528208058550 }" http://localhost:8080/subscribers

## Update
curl -X PUT -H "Content-Type: application/json" -d "{ \"msisdn\": \"1155972532644\", \"customerIdOwner\": 1, \"customerIdUser\": 1,  \"serviceType\": \"MOBILE_POSTPAID\", \"serviceStartDate\": 1528208058559 }" http://localhost:8080/subscribers/1

## Delete
curl -X DELETE -H "Content-Type: application/json" http://localhost:8080/subscribers/1


# Expenses 360

- Manage Your expenses by Month

## Tech Stack
- Thymeleaf
- Java 21
- Springboot 3.3.2
- Postgres
- Google OAuth

## Prerequisite Installation
- [Java 21](https://www.oracle.com/java/technologies/downloads/#java21) 
- Postgres
  - Download and install from https://www.postgresql.org/download/ 
  - Alternatively, Postgres can be run via docker - [Steps](docs/postgres-steps.md)
  
## Database Setup 
 - Create a local postgres database with name - *expense_management*
 - Run [db script](./src/main/resources/db/postgres/schema.sql)
   - Add VM Arguments for db username and password 
     ```
      -Dspring.datasource.username=REPLACE_ME
      -Dspring.datasource.password=REPLACE_ME
     ```

## OAuth Setup
  - Create Client ID and Client Secret in Google Console - [Steps](docs/oauth-setup-steps.md)
  - Add these VM arguments to Enable Google OAuth in dev/test environment
    
    ```
    -Dspring.security.oauth2.client.registration.google.client-id=REPLACE_ME
    -Dspring.security.oauth2.client.registration.google.client-secret=REPLACE_ME
    ```
    
### VM Arguments add sample for intellij IDE
![](docs/vm-arguments.png)


## Containerization
 - [Dockerfile](Dockerfile)
 - [Containerization Reference](https://docs.docker.com/language/java/)

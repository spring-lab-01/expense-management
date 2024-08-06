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

## Setup in Local to Run in IDE - Skip this step if you want to run this using maven  
### Step 1. Database Setup 
 - Create a local postgres database with name - *expense_management*
   - Add VM Arguments for db username and password 
     ```
      -Dspring.datasource.username=REPLACE_ME
      -Dspring.datasource.password=REPLACE_ME
     ```

### Step 2. OAuth Setup
  - Create Client ID and Client Secret in Google Console - [Steps](docs/oauth-setup-steps.md)
  - Add these VM arguments to Enable Google OAuth in dev/test environment
    
    ```
    -Dspring.security.oauth2.client.registration.google.client-id=REPLACE_ME
    -Dspring.security.oauth2.client.registration.google.client-secret=REPLACE_ME
    ```
    
### VM Arguments add sample for intellij IDE
![](docs/vm-arguments.png)


### Run Using Docker Compose 
 #### Step 1: Create Client ID and Client Secret in Google Console - [Steps](docs/oauth-setup-steps.md)
 #### Step 2: Add a folder .env with three files in it
   - db_password.txt - add postgres password in it
   - db_user.txt - add database username
   - server.env
     - GOOGLE_CLIENT_ID=REPLACE_ME
     - GOOGLE_CLIENT_SECRET=REPLACE_ME
     - POSTGRES_USER=REPLACE_ME
     - POSTGRES_PASSWORD=REPLACE_ME
 #### Step 2. Run command in terminal inside expense-management folder
```bash
docker compose up --build
```   

## Containerization References
- [Dockerfile](Dockerfile)
- [Docker Tutorial Guide](https://docs.docker.com/language/java/)
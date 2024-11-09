# Postgres can be setup using 2 ways 

## 1. Download and install from https://www.postgresql.org/download/

## 2. Run via docker
- Pull postgres image -
```
docker pull postgres
```
- Run docker container - 
```
docker run --name local-postgres -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
```
* It will start docker container with name `local-postgres` at port `5432` with username `postgres` and password `postgres`

## Install psql in mac (Optional)
```
brew uninstall postgresql
```
```
brew install libpq
```
```
brew link --force libpq
```

## connect to local database using psql
```
psql -h localhost -p 5432 -U postgres
```

### psql commands

- list all database - 
```
\l
```
- connect to a database
```
\c <database_name> 
```

### Create database if not available
```
create database expense_management;
```
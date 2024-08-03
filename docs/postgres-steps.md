## Postgres can be used via docker - below steps are performed in mac but can be used in linux/windows with change in psql 
## Start Postgres container
- docker pull postgres
- docker run --name local-postgres -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres

## Install psql (Optional)
- brew uninstall postgresql
- brew install libpq
- brew link --force libpq

## connect to local database using psql
psql -h localhost -p 5432 -U postgres

### psql commands
\l - list all database
\c <database_name> - connect to a database
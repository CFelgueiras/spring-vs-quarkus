# Spring vs Quarkus

The goal of this repository is to provide a reduced number of Quarkus applications that are able to show its key points concisely while comparing them to a traditional Spring application:
1. spring-rest-api is built with Spring. It provides a REST API and persists data on a PostgreSQL database;
2. quarkus-rest-api is built with Quarkus in an imperative way. It provides a REST API, persists data on a PostgreSQL database, generates an OpenAPI specification while providing a representation using Swagger UI and generates metrics on OpenMetrics specification while providing a representation using Prometheus;
3. quarkus-reactive-rest-api is built with Quarkus in a reactive way. It provides a REST API, persists data on a PostgreSQL database, generates an OpenAPI specification while providing a representation using Swagger UI and generates metrics on OpenMetrics specification while providing a representation using Prometheus.

# Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites

```bash
Java 8
Maven 3.6.1
Docker 19.03.5
```

# Running

## Create Docker network
```bash
$ docker network create book-store-network
```

## Build & Run Spring application
Generate fat .jar:
```bash
$ mvn clean package spring-boot:repackage
```

Build docker compose w/ database (PostgreSQL) and application (Spring):
```bash
$ docker-compose build
```

Run docker compose w/ database (PostgreSQL) and application (Spring):
```bash
$ docker-compose up
```

## Build & Run Quarkus applications
Generate fat .jar:
```bash
$ mvn clean package
```

Or generate native executable:
```bash
$ mvn package -Pnative -Dnative-image.docker-build=true
```

Build docker compose w/ database (PostgreSQL), migrations (Flyway), metrics (Prometheus) and application (Quarkus):
```bash
$ docker-compose build
```

Run docker compose w/ database (PostgreSQL), migrations (Flyway), metrics (Prometheus) and application (Quarkus):
```bash
$ docker-compose up
```                          

## Run stress tests
Run Apache Benchmark stress tests:
```bash
$ ab -n 10000 -c 10 http://localhost:8080/api/v1/books 
```

If you don't have Apache Benchmark, run the following Docker container:
```bash
$ docker run --rm jordi/ab -n 10000 -c 10 http://localhost:8080/api/v1/books/ 
```

# Spring vs Quarkus
Just some simple REST APIs:
1. spring-rest-api is built with Spring. It provides a REST API and persists data on a PostgreSQL database;
2. quarkus-rest-api is built with Quarkus in an imperative way. It provides a REST API and persists data on a PostgreSQL database;
3. quarkus-reactive-rest-api is built with Quarkus in a reactive way. It provides a REST API, persists data on a PostgreSQL database, generates a OpenAPI specification while providing a representation using Swagger and generates metrics on OpenMetrics specification while providing a representation using Prometheus.

## PostgreSQL
Run docker container:
```bash
$ docker run --name book-store-db -p 5432:5432 -e POSTGRES_PASSWORD=postgres --network book-store-network -d postgres:alpine
```

## Spring Rest API
Generate fat .jar:
```bash
$ mvn package spring-boot:repackage
```
Build docker image:
```bash
$ docker build -t spring/book-store .
```

Run docker container:
```bash
$ docker run -i --rm --name book-store-spring-api -p 8080:8080 --network book-store-network spring/book-store
```

## Quarkus Rest API
Generate native executable:
```bash
$ mvn package -Pnative -Dnative-image.docker-build=true
```

Build docker image:
```bash
$ docker build -t quarkus/book-store .
```

Run docker container:
```bash
$ docker run -i --rm --name book-store-quarkus-api -p 8081:8080 --network book-store-network quarkus/book-store
```

## Quarkus Rest API (Reactive)
Generate native executable:
```bash
$ mvn package -Pnative -Dnative-image.docker-build=true
```

Build docker image:
```bash
$ docker build -t quarkus/reactive-book-store .
```

Run docker container:
```bash
$ docker run -i --rm --name book-store-quarkus-reactive-api -p 8082:8080 --network book-store-network quarkus/reactive-book-store
```

## Prometheus
Run docker container (don't forget to change user folder location):
```bash
$ docker run --name prometheus -p 9090:9090 -v {USER_FOLDER}/prometheus.yml:/etc/prometheus/prometheus.yml --network book-store-network -d prom/prometheus
```

## Database seed
Run .sql script:
```sql
DROP TABLE book;
CREATE TABLE IF NOT EXISTS book ( id     UUID         NOT NULL
                                , author VARCHAR(255) NOT NULL
                                , title  VARCHAR(255) NOT NULL
                                , PRIMARY KEY(id)
                                );
```                                

## Stress tests
Run Apache Benchmark stress tests:
```bash
$ ab -n 1000 -c 10 http://localhost:8080/api/v1/books 
```

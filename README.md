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

## Prometheus
Run docker container:
```bash
$ docker run --name prometheus -p 9090:9090 -v /Users/i2sjfl/IdeaProjects/spring-vs-quarkus/quarkus-demo/prometheus.yml:/etc/prometheus/prometheus.yml --network book-store-network -d prom/prometheus
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

# Spring vs Quarkus
Just two simple REST APIs (one built w/ Spring &amp; the other one w/ Quarkus) communicating with a PostgreSQL database.

## PostgreSQL
Run docker container:
```bash
$ docker run --name book-store-db -p 5432:5432 -e POSTGRES_PASSWORD=postgres --network book-store-network -d postgres:alpine
```

## Spring
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

## Quarkus
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
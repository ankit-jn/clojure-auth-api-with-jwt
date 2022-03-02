# clojure-auth-api-with-jwt

This is an authentication service written in clojure backed by JWT mechanism

## The project was created using compojure-api template

`lein new compojure-api clojure-auth-api-with-jwt` 

## Usage

### Run the application locally

`lein ring server`
or
`lein run`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/auth-service.jar
```

### Packaging as war

`lein ring uberwar`

### Swagger document

Swagger - `http://<Host IP>:8080/index.html`

### API Base endpoint (for the rest refer swagger)

`http://<Host IP>:8080/api/auth`

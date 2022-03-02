# clojure-auth-api-with-jwt

This is an authentication service written in clojure backed by JWT mechanism

## The project was created using compojure-api template

`lein new compojure-api clojure-auth-api-with-jwt` 

## Prerequisites

### generate Private Key

`openssl genrsa -aes128 -out auth_privkey.pem 2048`

<Give your passphrase while you are running the above command>
  
### generate Public Key using Private Key
  
`openssl rsa -pubout -in auth_privkey.pem -out auth_pubkey.pem`

### How to use the the above keys and passphrase

1. Place the generate files (auth_privkey.pem and auth_pubkey.pem) in resources folder of the project
2. Replace `<YOUR PASSPHRASE>` with you passphrase value in the following file          
   
  `clojure-auth-api-with-jwt\src\com\arjstack\tech\auth\security\token_security.clj`
  
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

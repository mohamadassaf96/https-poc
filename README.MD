# Setup
* Spring boot application
* Mocked LDAP server for a dummy login API

To Launch the application, navigate to the root folder and run

```mvn package```

```mvn spring-boot:run```

Spring boot will run on port 8080

# Demo
* Open Postman
* Call GET http://localhost:8080/login
    * You will get an error ```This combination of host and port requires TLS.```
* Now modify the url to https://localhost:8080/login (https instead of http)
* Client on postman settings, make sure SSL verification is ON under "General"
* Go to "Certificates", turn ON "CA certificates", next to PEM file, load .cert/certificate.pem from this project
* Call the endpoint again
# Stocks Application

A Java / Maven / Spring Boot application.

## How to Run 

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Make sure you are using JDK 1.8 and Maven 3.x
* Can build the project and run the tests by running ```mvn clean package```
* Once successfully built, service can be run by:
```
        mvn spring-boot:run
```

## About the Service

The service is a Stocks REST service. It uses an in-memory database (H2) to store the data.


You can use this sample service to understand the conventions and configurations that allow you to create a DB-backed RESTful service. Once you understand and get comfortable with the sample app you can add your own services following the same patterns as the sample service.
 
Here is what this little application demonstrates: 



Following are the REST endpoints:

### Get information about system health:

```
http://localhost:8091/health
```

### Create a Stock resource

```
POST /api/stocks
Accept: application/json
Content-Type: application/json

{
  "currentPrice": 11,
  "name": "StockName"
}
RESPONSE: HTTP 201 (Created)
Location header: /api/stocks/1
```

### Retrieve a paginated list of stocks

```
http://localhost:8080/api/stocks?pageNumber=1&pageSize=10
Response: HTTP 200
Content: paginated list 
```

### Update a Stock resource

```
PUT /api/stocks/{id}
Accept: application/json
Content-Type: application/json

{
  "currentPrice": 11,
  "name": "StockName"
}

RESPONSE: HTTP 200 (OK)
```
### To view Swagger 2 API docs

Run the server and browse to localhost:8080/swagger-ui.html


### To view H2 in-memory datbase

Browse to http://localhost:8080/h2-console. Default username is 'sa' with a blank password.

# Contact: hnimbark90@gmail.com



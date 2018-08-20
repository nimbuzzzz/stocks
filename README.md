# Stocks Application

Built using  Java / Maven / Spring Boot / H2 DB.

## How to Run 

This application is packaged as a war which has Tomcat 8 embedded.

* Requirements:  JDK 1.8 and Maven 3.x
* Can build the project and run the tests by running ```mvn clean package```
* Once successfully built, service can be run by:
```
        mvn spring-boot:run
```

## About the Service

The service is a Stocks REST Application. It uses an in-memory database (H2) to store the data.


Following are the REST endpoints with sample request & response:

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
Response Body:
{
  "id": 12,
  "name": "second",
  "currentPrice": 2.234,
  "updated": "2018-08-20T18:23:14.454+0000"
}
```

### Retrieve a single stock

```
GET /api/stocks/12
RESPONSE: HTTP 200
Response Body:
{
  "id": 12,
  "name": "second",
  "currentPrice": 2.23,
  "updated": "2018-08-20T18:23:14.454+0000"
}
```


### Retrieve a paginated list of stocks

```
GET /api/stocks?pageNumber=0&pageSize=10
RESPONSE: HTTP 200
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
Response Body:
{
  "id": 11,
  "name": "first",
  "currentPrice": 68,
  "updated": "2018-08-20T19:01:02.982+0000"
}
```
### To view Swagger 2 API docs

Run the server and browse to http://localhost:8080/swagger-ui.html


### To view H2 in-memory datbase

Browse to http://localhost:8080/h2-console. Default username is 'sa' with a blank password.

# Contact: hnimbark90@gmail.com



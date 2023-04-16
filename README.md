# TODO Project

Demo RESTful TODO project for Spring Boot with Kotlin.
As this project is in progress, this documentation as the requirements should 
change as lessons are learned and new requirements are needed.

## Abstract
This project is a study case for Spring Boot with Kotlin.
The goal is to create a TODO RESTful app while learning the following technologies:
- Kotlin
- Maven
- Spring Web
- Spring HATEOAS
- Spring Data JPA
- Flyway
- Testcontainers

## The Project
The main goal is to create a step-by-step guide on how to build a RESTful service 
with Kotlin and spring.
It should:
- Use maven to maintain the dependencies
- Use Kotlin as main language
- Use Spring Web and HATEOAS to provide RESTful endpoints
- Use Spring Data JPA to connect to the databse
- Use Flyway to control the database migrations
- Use Testcontainers for integration tests

## The Steps
### 1 - Project creation
The first step is to create a new spring project using https://start.spring.io
The project should add the following dependencies:
- Spring Boot DevTools
- Spring Web
- Spring HATEOAS
- Spring Data JPA
- Flyway Migration
- Testcontainers

### 2 - Provide Endpoints
The second step is to create the following endpoints, persisting the data on memory:
- Create a new TODO (POST: /todo)
```
POST: localhost:8080/todo
request: {
    "name": "Read Kotlin in Action",
    "description": "Read chapter 6: The Kotlin type system",
    "status": "TODO"
}
---
response: 201 - Created.
{
    "id": "hzAoI0",
    "name": "Read Kotlin in Action",
    "description": "Read chapter 6: The Kotlin type system",
    "status": "TODO"
}
```
- List the existing TODOs (GET: /todo)
```
GET: localhost:8080/todo
request: 
---
response: 200 - Ok
{
    todos: [{
        "id": "koLfRh",
        "name": "Read Kotlin in Action",
        "description": "Read chapter 5: Programming with lambdas",
        "status": "DONE"
    },{
        "id": "hzAoI0",
        "name": "Read Kotlin in Action",
        "description": "Read chapter 6: The Kotlin type system",
        "status": "TODO"
    }]
}
```
- Retrieve information for a specific TODO (GET: /todo/id)
```
GET: localhost:8080/todo/koLfRh
request: 
---
response: 200 - Ok
{
    todo: {
        "id": "koLfRh",
        "name": "Read Kotlin in Action",
        "description": "Read chapter 5: Programming with lambdas",
        "status": "DONE"
    }
}
```
- Edit an existing TODO (PUT: /todo/id)
```
PUT: localhost:8080/todo/hzAoI0
request: {
    "name": "Read Kotlin in Action",
    "description": "Read chapter 6: The Kotlin type system",
    "status": "DONE"
}
---
response: 200 - Ok
{
    "id": "hzAoI0",
    "name": "Read Kotlin in Action",
    "description": "Read chapter 6: The Kotlin type system",
    "status": "DONE"
}
```
- Delete an existing TODO (DELETE: /todo/id)
```
DELETE: localhost:8080/todo/hzAoI0
request: 
---
response: 204 - No content
```

### 3 - Store on Database
The third step is to create the database and migrate the logic to store/retrieve 
the data from memory to the database. This step should:
- Create the flyway migration to the first version of the database
- Connect to the DB through the Spring Data JPA API.
- Store the TODOs on database
- Retrieve TODOs from database

### 4 - Retrieve hypermedia information
The fourth step should add the hypermedia metadata to our endpoint returns:
- Create a new TODO (POST: /todo)
```
POST: localhost:8080/todo
request: {
    "name": "Read Kotlin in Action",
    "description": "Read chapter 6: The Kotlin type system",
    "status": "TODO",
    "_links": {
        "self": { "href": "/todo/hzAoI0" },
        "list": { "href": "/todo}
    }
}
---
response: 201 - Created.
{
    "id": "hzAoI0",
    "name": "Read Kotlin in Action",
    "description": "Read chapter 6: The Kotlin type system",
    "status": "TODO"
}
```
- List the existing TODOs (GET: /todo)
```
GET: localhost:8080/todo
request: 
---
response: 200 - Ok
{
    todos: [{
        "id": "koLfRh",
        "name": "Read Kotlin in Action",
        "description": "Read chapter 5: Programming with lambdas",
        "status": "DONE"
    },{
        "id": "hzAoI0",
        "name": "Read Kotlin in Action",
        "description": "Read chapter 6: The Kotlin type system",
        "status": "TODO"
    }],
    "_links": {
        "self": { "href": "/todo" },
    }
}
```
- Retrieve information for a specific TODO (GET: /todo/id)
```
GET: localhost:8080/todo/koLfRh
request: 
---
response: 200 - Ok
{
    todo: {
        "id": "koLfRh",
        "name": "Read Kotlin in Action",
        "description": "Read chapter 5: Programming with lambdas",
        "status": "DONE"
    }
}
```
- Edit an existing TODO (PUT: /todo/id)
```
PUT: localhost:8080/todo/hzAoI0
request: {
    "name": "Read Kotlin in Action",
    "description": "Read chapter 6: The Kotlin type system",
    "status": "DONE"
}
---
response: 200 - Ok
{
    "id": "hzAoI0",
    "name": "Read Kotlin in Action",
    "description": "Read chapter 6: The Kotlin type system",
    "status": "DONE",
    "_links": {
        "self": { "href": "/todo/hzAoI0" },
        "list": { "href": "/todo}
    }
}
```
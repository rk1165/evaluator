### Simple BODMAS Mathematical Expression Evaluator Service

- This code base contains REST service for following:
  - Given a mathematical expression, evaluate it and return the result
  - Given a user_id return the most frequent character used by the user since beginning

- There are two ways to run the code base:
  - Extract the code from the zip archive. `cd` into the folder 
  - Clone it from github using `git clone https://github.com/rk1165/calculator.git` `cd` into the folder
  - In both cases run the command './mvnw spring-boot:run'
 
- There are two API endpoints in the service :
  - `http://localhost:8080/api/calculate`
  - `http://localhost:8080/api/frequent`
  - Former is a POST request and the later GET request.

- To test the first API we need a `RequestBody`. A sample payload for the same is 
```json
{
    "userId":"abc",
    "expression" : "23 - (17 * 2 - (10 / 2))"
}
```

- To test the second API we need a query parameter. A sample request is in the form
`http://localhost:8080/api/frequent?userId=abc`

- Response is in the format of
```json
{
  "response": "int/char"
}
```
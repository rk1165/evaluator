### Simple BODMAS Mathematical Expression Evaluator Service

- This is a simple SpringBoot repo which contains REST endpoints for following operations:
  - Given a mathematical expression, evaluate it and return the result.
  - Given a `user_id`, return the most frequent operator used by them.
  - Given a `user_id`, return the counts of all the operators user by them.

### How to run it locally?
- Use `sh run.sh` command to boot up the application
 
- There are two API endpoints in the service :
  - **POST** : `http://localhost:8080/api/v1/expression`
  - **GET** : `http://localhost:8080/api/v1/frequent`
  - **GET** : `http://localhost:8080/api/v1/count`

- To test the first API we need a `RequestBody`. A sample payload for the same is 
```json
{
    "userId":"abc",
    "expression" : "23 - (17 * 2 - (10 / 2))"
}
```

- To test the second and third API we need a query parameter. 
- Sample Requests for both of them:
  - `http://localhost:8080/api/v1/frequent?userId=abc`
  - `http://localhost:8080/api/v1/count?userId=abc`

- Response is in the format of
```json
{
  "response": "int/char"
}
```

- Design and development is mentioned in the file `design_and_dev.md`
- There is also a simple html file `calculate.html` which can be used to test the API from a browser.
 
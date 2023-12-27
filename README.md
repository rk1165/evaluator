### Simple BODMAS Mathematical Expression Evaluator Service

- This is a simple SpringBoot repo which contains REST endpoints for following operations:
    - Given a mathematical expression, evaluate it and return the result.
    - Given a `user_id`, return the most frequent operator used by that user.
    - Given a `user_id`, return the counts of all the operators used by that user.
- It uses H2 as an in-memory database.
- `awsInfra` is used to provision infra on AWS to deploy this project and run it in AWS FARGATE.
- **GitHub Actions** is used for deploying it to AWS using Docker.

### How to run and test it locally?

- Use `bash run.sh` command to boot up the application.
- Use `bash test.sh` to run curl commands against the various REST endpoints.
- Design and development is mentioned in the file `design_and_dev.md`
- There is also an html file `calculate.html` which can be used to test the API from a browser.



https://github.com/rk1165/evaluator/assets/8726141/be7da065-0cfc-4da6-a6c5-76c505b71aee


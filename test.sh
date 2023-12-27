#!/usr/bin/env bash

curl -X POST --location "http://localhost:8080/api/v1/expression" \
    -H "Content-Type: application/json" \
    -d '{
          "userId": "rav",
          "expression": "1 + (2 * (6 - 5) + (23 * 29)) + (80 / 4)"
        }' | json_pp

echo "----------------------------------"
curl -X POST --location "http://localhost:8080/api/v1/expression" \
    -H "Content-Type: application/json" \
    -d '{
          "userId": "sau",
          "expression": "23 - (17 * 2 - (10 / 2))"
        }' | json_pp
echo "----------------------------------"

curl -X POST --location "http://localhost:8080/api/v1/expression" \
    -H "Content-Type: application/json" \
    -d '{
          "userId": "rav",
          "expression": "2 ^ 4 ^ 2"
        }' | json_pp
echo "----------------------------------"

curl -X POST --location "http://localhost:8080/api/v1/expression" \
    -H "Content-Type: application/json" \
    -d '{
          "userId": "tom",
          "expression": "1 + 2 + 3 + 4 + 5 + (6 / 3)"
        }' | json_pp
echo "----------------------------------"

curl -X GET --location "http://localhost:8080/api/v1/frequent?userId=rav" | json_pp
echo "----------------------------------"

curl -X GET --location "http://localhost:8080/api/v1/frequent?userId=sau" | json_pp
echo "----------------------------------"

curl -X GET --location "http://localhost:8080/api/v1/count?userId=rav" | json_pp
echo "----------------------------------"

curl -X GET --location "http://localhost:8080/api/v1/count?userId=sau" | json_pp
echo "----------------------------------"



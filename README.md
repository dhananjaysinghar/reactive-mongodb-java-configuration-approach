# mongo-db-example

## Docker command to create mongodb in local
~~~
docker run --name mongodb -p 27017:27017 -e MONGODB_USERNAME=test_user -e MONGODB_ROOT_PASSWORD=root_password -e MONGODB_PASSWORD=test_password -e MONGODB_DATABASE=test_database -d bitnami/mongodb:latest
~~~

## POST Request
~~~
curl --location --request POST 'http://localhost:8080/bookings' \
--header 'Content-Type: application/json' \
--data-raw '{
    "origin": "CHENNAI",
    "destination": "MUMBAI",
    "date": "20/11/2022",
    "shipper": "ABC",
    "consignee": "XYZ"
}'
~~~


## GET Request
~~~
curl --location --request GET 'http://localhost:8080/bookings'
curl --location --request GET 'http://localhost:8080/bookings/3b633d2d-3e26-4258-bff5-1603cd1e3e02'
~~~
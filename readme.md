# BackBase API

## Configuration

- Java 8
- Tomcat 7
- Maven 3.6.3

## Start Application

Use the command ```mvn -Dmaven.tomcat.port=8081 org.apache.tomcat.maven:tomcat7-maven-plugin:2.2:run``` in the root path


## Usage

The first step is login. Do a post to the path ```http://localhost:8081/backbase/api/authenticate```. It will return a token. After that you can do any request below.

Credentials:
- username: admin
- password: admin

Example

```cmd
curl --location --request POST 'http://localhost:8081/backbase/api/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "admin",
    "password": "admin"
}'
```

#### Headers

It's necessary to pass the token every request\
\
```Authorization: Bearer <token>```

## Routes

#### Transactions list:
- Method: GET
- Path: ```http://localhost:8081/backbase/api/transactions/account/<account-id>```

Example: 

```cmd
curl --location --request GET 'http://localhost:8081/backbase/api/transactions/account/savings-kids-john' \
--header 'Authorization: Bearer <token>'
```

#### Transaction filter based on transaction type

- Method: GET
- Path: ```http://localhost:8081/backbase/api/transactions/account/<account-id>/transaction-type/<transaction-type>```

Example:

```cmd
curl --location --request GET 'http://localhost:8081/backbase/api/transactions/account/savings-kids-john/transaction-type/sepa' \
--header 'Authorization: Bearer <token>'
```

  
#### Total amount for transaction type
- Method: GET
- Path: ```http://localhost:8081/backbase/api/transactions/account/<account-id>/transaction-type/<transaction-type>/amount```

Example: 

```cmd
curl --location --request GET 'http://localhost:8081/backbase/api/transactions/account/savings-kids-john/transaction-type/sepa/amount' \
--header 'Authorization: Bearer <token>'
```

## Test

To execute the tests, run the command ```mvn clean test jacoco:report``` in the root folder. It will execute the tests and generate a coverage report in the path: ```backbase\target\site\jacoco\index.html```. To see that, you need only open the index.html.

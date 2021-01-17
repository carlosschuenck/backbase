# BackBase API

## Configuration

- Java 8
- Tomcat 7

## Start Application

Use the command ```mvn -Dmaven.tomcat.port=8081 org.apache.tomcat.maven:tomcat7-maven-plugin:2.2:run``` in the root path


## Usage

The first step is login. Do a post to the path ```http://localhost:8081/backbase/api/authenticate```. It will return a token. After that you can do any request below

#### Header

It's necessary to pass the token every request\
\
```Authorization: Bearer <token>```


## Routes


#### Transactions list:
- Method: GET
- Path: ```http://localhost:8081/backbase/api/transactions/account/<account-id>```
- Example: ```http://localhost:8081/backbase/api/transactions/account/savings-kids-john```


#### Transaction filter based on transaction type

- Method: GET
- Path: ```http://localhost:8081/backbase/api/transactions/account/<account-id>/transaction-type/<transaction-type>```
- Example: ```http://localhost:8081/backbase/api/transactions/account/savings-kids-john/transaction-type/sepa```


  
#### Total amount for transaction type
- Method: GET
- Path: ```http://localhost:8081/backbase/api/transactions/account/<account-id>/transaction-type/<transaction-type>/amount```
- Example: ```http://localhost:8081/backbase/api/transactions/account/savings-kids-john/transaction-type/sepa/amount```

## Test

To execute the tests, run the command ```mvn clean test jacoco:report``` in the root folder. It will execute the tests and generate a coverage report in the path: ```backbase\target\site\jacoco\index.html```. To see that, you need only open the index.html.

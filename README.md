# Getting Started

## MVN Startup
- `mvn clean tests package spring-boot:repackage spring-boot:run` 
- A `development` profile to run locally

## H2-Database
- URL
    - http://localhost:8080/h2-console/

## Swagger
- URL
    - http://localhost:8080/swagger-ui.html
    
## API
### GET
- List all users
    - http://localhost:8080/api/v1/admin/users
- Get user by id
    - http://localhost:8080/api/v1/admin/user/{id}    
- Get user by login
    - http://localhost:8080/api/v1/admin/user/login/{login}
    
### POST
- Create a new user
    - http://localhost:8080/api/v1/admin/user
    - Body:
    ```
    {
        "id":1,
        "name":"user 1",
        "email":"user1@mail.com",
        "login":"login_1"
    }
    ```
### DELETE
- Delete an user
    - localhost:8080/api/v1/admin/user/delete/{id}
    
### PUT
- Edit an user    
    
## RabbitMQ
- URL: http://localhost:15672/
- Credentials:
    - The defalut login and password is `guest`
    
### POST   
- Basic message sending:
    - Send a new message:
        - http://localhost:8080/api/v1/message/user1@mail.com
    - Body:
    ```
    {
        "message": "Olá Rabbit!"
    }
    ```
 - Queue message example:
   ```
   {
       "message":"Mensagem de : {
            "message": "Olá Rabbit"},
            "createdDate":1637203374071
       }
   }
   ```
   
 ## Throubeshoot
 - Kill a used port:
    - 
    ```
       netstat -aon | findstr 8080
       taskkill /f /pid 18864
    ``` 
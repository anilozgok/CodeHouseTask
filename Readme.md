## Code House Back End Task

### Definition

Goal of the project is  creating a simple phone book application
which you can achieve basic actions such as adding new contact,
editing contact and deleting contact from phone book.

This Back end project developed using MVC architecture. 

We are using Java, Spring Boot and PostgreSQL. Also, we can access pgadmin to connect database.


### Requirements
* Docker: for installation
* Java 17+: for development
* Maven: for development


### Installation

You can directly run the project using docker-compose

```shell
docker-compose up
```



### Using project

To add new contact 
```http request
http://localhost:8080/api/phone-book/contact/add/

Sample Request Body:
{
    "firstName": "Anıl",
    "lastName": "Can",
    "phoneNumber":"0555 555 5555",
    "gender": "MALE"
}
```

To edit contact
```http request
http://localhost:8080/api/phone-book/contact/edit/0555 555 5555/

Sample Request Body:
{
    "firstName": "Anıl Can",
    "lastName": "Özgök",
    "phoneNumber":"0555 555 5555",
    "gender": "MALE"
}
```

To delete contact
```http request
http://localhost:8080/api/phone-book/contact/delete/0555 555 5555/
```

Also you can find HTTP request on postman collection file.

To access pgadmin panel

```http request
http://localhost:5050/

username=test@test.com
password=test
```
## Code House Back End Task

Back end for simple phone book application. 
We are using Java, Spring Boot and PostgreSQL.
And developed with MVC architecture.
Goal of the project is  creating a simple phone book application
which you can achieve basic actions such as adding new contact,
editing contact and deleting contact from phone book. 


### Requirements
* Docker: for installation
* Java 17+: for development
* Maven: for development

First install the project using maven

`mvn clean install`

Build the Dockerfile 

`docker build -t code-house-task .`

Run the docker image

`docker run -p 9090:8080 --name code-house-task code-house-task`

Or you can directly run the docker compose

`docker-compose up`

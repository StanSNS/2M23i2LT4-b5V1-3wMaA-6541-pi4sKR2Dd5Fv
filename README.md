# Project Overview

## Backend

- **Spring Boot**: Version 3.2.0 project based on the `spring-boot-starter-parent` for configuration.
- **Spring Framework**: Includes various Spring dependencies like:
    - `spring-boot-starter-data-jpa`
    - `spring-boot-starter-validation`
    - `spring-boot-starter-web`
- **Database**: MySQL database with the `mysql-connector-j` dependency.
- **Project Lombok**: A library that simplifies the creation of Java classes by reducing boilerplate code.
- **Model Mapping**: Facilitates object-to-object mapping.
- **Gson**: A library for JSON serialization and deserialization.
- **JUnit and AssertJ**: Used for testing and test assertions.

### Testing

- Below, you will find the coverage percentage obtained from the JUnit testing.

![test coverage](https://i.imgur.com/tHN7yw3.png)

## Project Setup Instructions

1. Make sure to clone or download the whole project.

### Backend (Spring Boot):

1. **Install Java:**
    - Make sure you have Java 17 installed on your computer:
      [Java 17 official download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)


2. **Install Maven:**
    - Install Maven on your computer. You can download it
      from [Maven official website](https://maven.apache.org/download.cgi).


3. **Install MySQL Community Server:**
    - Install MySQL Server on your computer. You can download it
      from [MySQL official website](https://dev.mysql.com/downloads/installer/).


4. **Open the backend project**
    - Go to **application.properties** file and change the **spring.datasource.(username & password)** values to your
      MySQL
      credentials


5. **Run the backend server:**
    - Open **FxibBackendApplication.java** class - right click and run the program.

This will build your project and start the Spring Boot application.

5. **Verify:**
    - Open a web browser and go to [localhost:8000](http://localhost:8000) to verify that your backend is running.

## Access the service data

### In order to get access to the data the spring boot app must work!

BASE URL = **http://localhost:8000/**

1. getMaxDieselPrice() => [Link](http://localhost:8000/max?fuelType=diesel)
2. getMinE5Price() => [Link](http://localhost:8000/min?fuelType=e5)
3. getAvgE10Price() => [Link](http://localhost:8000/avg?fuelType=e10)
4. getAllDataByName() => [Link](http://localhost:8000/findByName?name=Q1%20Tankstelle) (Find Gas Station By Name: **"Q1 Tankstelle"**)


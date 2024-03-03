# Simple Mail Sender Project

This is a simple Java and Spring-based mail sender project that allows you to send emails using Spring Boot and the JavaMail API.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Configuration](#configuration)
    - [Running the Application](#running-the-application)
- [Usage](#usage)

## Features

- Send emails using Spring Boot and JavaMail API.
- Easy to configure with Spring Boot application properties.

## Getting Started

### Prerequisites

To run this project, you need to have the following installed on your machine:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

### Configuration

Before running the application, you need to configure your email settings in the `application.properties` file. Open the `src/main/resources/application.properties` file and set the following properties:

```properties
spring.mail.host=your_smtp_host
spring.mail.username=your_email@gmail.com
spring.mail.password=your_email_password
spring.mail.properties.mail.transport.protocol=smtp
spring.mail.properties.mail.smtp.port=587
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

Replace `your_smtp_host`, `your_email@gmail.com`, and `your_email_password` with your SMTP server, email address, and password.

## Running the Application
You can run the application using the following Maven command:
```bash
mvn spring-boot:run
```
The application will start on `http://localhost:8080`.

## Usage
Once the application is running, you can send emails by making a POST request to the /mail/send endpoint. You can use tools like cURL, Postman, or your preferred HTTP client for this purpose.

Example cURL command:
```bash
curl -X POST \
  http://localhost:8080/mail/send \
  -d 'email=recipient@example.com&subject=Test Subject&body=Hello, this is a test email!'
```
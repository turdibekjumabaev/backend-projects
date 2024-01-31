# English Dictionary API

This project provides a simple REST API for accessing English dictionary entries. The dictionary is backed by a SQLite database, and the API is built using Flask.

## Table of Contents

- [Database Schema](#database-schema)
- [REST API Endpoints](#rest-api-endpoints)
  - [1. Get Word Information](#1-get-word-information)
  - [2. Get Word Suggestions](#2-get-word-suggestions)
  - [3. Miscellaneous](#3-miscellaneous)
- [Running the Application](#running-the-application)

## Database Schema

### `entries` Table:

- **word (varchar(25), NOT NULL, COLLATE NOCASE):** Represents the word being stored in the dictionary. Case-insensitive collation is applied.

- **wordtype (varchar(20), NOT NULL, COLLATE NOCASE):** Represents the type or category of the word (e.g., noun, verb, adjective). Case-insensitive collation is applied.

- **definition (varchar, NOT NULL, COLLATE NOCASE):** Represents the definition or meaning of the word. Case-insensitive collation is applied.

## REST API Endpoints

### 1. Get Word Information

- **Endpoint:** `/api/get/<word>`
  
- **Method:** GET

- **Parameters:**
  - `<word>` (string): The English word for which information is requested.

- **Response:**
  - If the word is found in the database:
    - Status Code: 200 OK
    - Content: JSON object containing word information (word, wordtype, definition).
  - If the word is not found:
    - Status Code: 404 Not Found
    - Content: JSON object with an "error" key and a message indicating that the word was not found.

### 2. Get Word Suggestions

- **Endpoint:** `/api/suggestion/<beginswith>`
  
- **Method:** GET

- **Parameters:**
  - `<beginswith>` (string): The prefix of the words to suggest.

- **Response:**
  - Status Code: 200 OK
  - Content: JSON object containing an array of suggested words. Each suggestion is a dictionary containing word information (word, wordtype, definition).

### 3. Miscellaneous

- **Index Page:**
  - **Endpoint:** `/`
  - **Method:** GET
  - **Description:** Displays the base HTML template.

- **Teardown Appcontext:**
  - **Description:** Closes the database connection when the application context is torn down.

- **Error Handling:**
  - In case of a database error during a request:
    - Status Code: 500 Internal Server Error
    - Content: JSON object with an "error" key and a message indicating a database error.

## Running the Application

- **Method:** Run the script as the main module.
  ```bash
  python demo.py
  ```
- Install required libraries
  ```
  pip install flask gunicorn
  ```
- Clone the project
  ```
  git clone https://github.com/turdibekjumabaev/backend-projects.git
  ```
- Run the app
  ```
  gunicorn --bind localhost:5000 demo:app
  ```
- Access the app: [click here](http://localhost:5000/)

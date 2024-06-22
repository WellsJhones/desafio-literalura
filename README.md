# Desafio Literalura

## Overview

This Java application is part of the Desafio Literalura project, which aims to interact with the Gutendex API to search and manage book data. The main components of this application include:

- **Principal.java**: The main class that orchestrates the flow of the application.

### Key Components

- **LivroRepository**: A repository class for managing book data.
- **ConsumoApi**: A service class for consuming the Gutendex API ,but for test I'm using google books API.
- **ConverteDados**: A service class for converting data formats.(not in use)
- **Scanner**: Used for reading user input from the console.

### Constants

- **ENDERECO**: The base URL of the Gutendex API (`https://gutendex.com/`).
- **ENDERECO_BUSCA**: The endpoint for searching books (`books?search=`).

### Dependencies

- **org.json**: For parsing JSON data from the API.
- **com.fasterxml.jackson.core**: For JSON processing.

## Getting Started

To run this application, ensure you have Java installed on your system. Clone the repository, navigate to the project directory, and compile the `Principal.java` file. Run the compiled class to start the application.

## Usage

The application prompts the user for search queries to find books via the Gutendex API. Enter your search query when prompted, and the application will display the results.

## Contributing

Contributions to the Desafio Literalura project are welcome. Please read the contributing guidelines before submitting your contributions.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## configuration on DB
change or add the correct information 
- DB_HOST              here you put your host database
- DB_ALURA_DESAFIO     here your database name
- DB_PASSWORD          here database passaword 

## requerements 

- Java 22
- postgresql

## working screenshots

<img src="src\main\java\com\wells\desafio_literalura\img\Screenshot 2024-06-22 043730.png" width="500">
<img src="src\main\java\com\wells\desafio_literalura\img\Screenshot 2024-06-22 043801.png" width="500">
<img src="src\main\java\com\wells\desafio_literalura\img\Screenshot 2024-06-22 043830.png" width="500">
<img src="src\main\java\com\wells\desafio_literalura\img\Screenshot 2024-06-22 051206.png" width="500">
# BankMart-Web

BankMart-Web is an e-commerce backend demonstration tailored for banking products (like simple everyday accounts, high-interest savings, and credit cards). It exposes REST APIs for searching, reading, updating, adding, and deleting these products.

## Technology Stack

- **Java 21**
- **Spring Boot 3.5.6**
  - Spring Web (RESTful web services)
  - Spring Data JPA (Data persistence and repository layer)
- **H2 in-memory database** (for easy, embedded testing)
- **Lombok** (for minimizing boilerplate code in models/entities)
- **Maven Wrapper** (for isolated deterministic builds)

## Getting Started

To get started locally, you do not need to install Maven. You only need to have Java 21+ installed on your system.

### Running the App

1. Ensure the Maven Wrapper script is executable (mac/Linux):
   ```bash
   chmod +x mvnw
   ```
2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start, usually on `http://localhost:8080`. The H2 in-memory database is pre-populated with 10 dummy banking products.

### Running the Tests

To run the unit and integration tests:
```bash
./mvnw clean test
```

## API Endpoints Summary

All routes are prefixed with `/api`.

| HTTP Method | Endpoint                       | Description                                |
| ----------- | ------------------------------ | ------------------------------------------ |
| GET         | `/api/products`                | Get all products                           |
| GET         | `/api/product/{id}`            | Get a specific product by ID               |
| POST        | `/api/product`                 | Add a new product                          |
| PUT         | `/api/product/{id}`            | Update an existing product                 |
| DELETE      | `/api/product/{id}`            | Delete a product                           |
| GET         | `/api/products/search`         | Search products by keyword                 |
| GET         | `/api/product/{id}/image`      | Retrieve product image byte data           |

### Important Notes on Products API

- **Images:** Add/Update API require `@RequestPart MultipartFile imageFile`. However, when updating a product (`PUT /api/product/{id}`), the `imageFile` is **optional**. If omitted, the previously stored image for the product will be preserved.
- **Search Query Parameter:** The `search` endpoint uses a param: `/api/products/search?keyword={term}`.

This project aims to become a fullstack e-commerce application with a React frontend and a Spring backend.

### Tech stack:
- **Frontend** - React, TypeScript
- **Backend** - Java, Spring, Spring Boot, Spring Data JDBC
- **Database** - PostgreSQL, PgAdmin, Flyway
- **Containerization** - Docker
- **Version control** - Git, GitHub

### Todo:
1. Create all the necessary REST endpoints.
2. Use Formik on the frontend to get user data.
3. Enable users to send messages/notes with their orders using Kafka.
4. Implement Spring Security for authentication and authorization, along with OAuth and OIDC.
5. Implement the persistence layer with Spring Data JDBC.
6. Write unit tests and integration tests, get a good test coverage.
7. Write documentation using Swagger API.
8. Set up CI/CD with GitHub Actions or Jenkins.
9. Containerize the application using Docker, deploy the image to Azure free-tier.

### Major API endpoints:
#### Accessing the cart: */api/v1/cart/1*
```json
{
  "cartItemResponseDtoList": [
    {
      "productDto": {
        "title": "Kathmandu jacket",
        "description": "Lightweight, water-resistant jacket for summer.",
        "price": 60,
        "category": "MENS_CLOTHING",
        "review": null
      },
      "quantity": 3
    },
    {
      "productDto": {
        "title": "Yonex Shoes",
        "description": "Teal-colored elegant looking Yonex shoes.",
        "price": 120,
        "category": "MENS_CLOTHING",
        "review": null
      },
      "quantity": 4
    }
  ],
  "cost": 660
}
```

#### Placing an order: */api/v1/order*
```json
{
  "userId": 1,
  "orderItems": [
    {
      "productId": 3,
      "price": 45.99,
      "quantity": 5
    },
    {
      "productId": 2,
      "price": 60,
      "quantity": 10
    }
  ]
}
```

#### Fetching all the orders for a user: */api/v1/order/user/1*
```json
{
  "userResponseDto": {
    "firstname": "June",
    "lastname": "Doe",
    "email": "june@gmail.com"
  },
  "orderItemResponseDtoList": [
    {
      "title": "Yonex Shoes",
      "description": "Teal-colored elegant looking Yonex shoes.",
      "price": 120,
      "category": "MENS_CLOTHING",
      "quantity": 2,
      "createdAt": "2024-06-29T19:48:38.02948"
    },
    {
      "title": "Kathmandu jacket",
      "description": "Lightweight, water-resistant jacket for summer.",
      "price": 60,
      "category": "MENS_CLOTHING",
      "quantity": 10,
      "createdAt": "2024-06-29T19:46:15.480662"
    },
    {
      "title": "Levi's jeans",
      "description": "Simple and elegant Levi's pants.",
      "price": 45.99,
      "category": "MENS_CLOTHING",
      "quantity": 5,
      "createdAt": "2024-06-29T19:46:15.480662"
    }
  ],
  "cost": 1069.95
}
```
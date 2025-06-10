# 🛍️ SpringBoot E-Commerce Backend (PoC)

This is a proof-of-concept backend for a simple e-commerce platform built with **Spring Boot** and **H2 in-memory database**. The project provides a foundation to build scalable and modular e-commerce applications.

## 🚀 Features Implemented

- ✅ Product listing with details (name, brand, price, quantity, etc.)
- ✅ CRUD operations for products
- ✅ Category management
- ✅ H2 database integration
- ✅ RESTful API structure
- ✅ Error handling and logging


## 🔐 Upcoming Features

| Category              | Features                                                                 |
|-----------------------|--------------------------------------------------------------------------|
| **Authentication & Roles** | - User login and registration<br>- Role-based access (`ADMIN`, `CUSTOMER`, etc.)<br>- JWT-based stateless auth |
| **AI Enhancements**        | - Product recommendation system based on description similarity<br>- Auto-summarization of product descriptions<br>- Smart search using semantic matching |
| **Additional Features**    | - Order management<br>- User profile and address book<br>- Inventory alerts<br>- Integration with PostgreSQL for persistent data |


## 🧰 Tech Stack

- **Backend**: Spring Boot
- **Database**: H2 (in-memory, for PoC)
- **Build Tool**: Maven
- **API Docs**: Swagger *(coming soon)*


## 📦 Installation & Setup

```bash
# Clone the repository
git clone https://github.com/YaMinThwe6/springboot_poc.git
cd springboot_poc

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

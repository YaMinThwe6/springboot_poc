# 🛍️ SpringBoot E-Commerce Backend (PoC)

This is a proof-of-concept backend for a simple e-commerce platform built with **Spring Boot** and **H2 in-memory database**. The project provides a foundation to build scalable and modular e-commerce applications.

## 🚀 Features Implemented

- ✅ Product listing with details (name, brand, price, quantity, etc.)
- ✅ CRUD operations for products
- ✅ H2 database integration
- ✅ RESTful API structure
- ✅ Error handling and logging


## 🔐 Upcoming Features

| Category              | Features                                                                 |
|-----------------------|--------------------------------------------------------------------------|
| **Authentication & Roles** | - User login and registration<br>- Role-based access (`ADMIN`, `CUSTOMER`, etc.)<br>- JWT-based stateless auth |
| **AI Enhancements**        | - Product recommendation system based on description similarity<br>- Auto-summarization of product descriptions<br>- Smart search using semantic matching |
| **Additional Features**    | - Order management<br>- Category management<br>- User profile and address book<br>- Inventory alerts<br>- Integration with PostgreSQL for persistent data |


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

Application: http://localhost:8080

H2 Console: http://localhost:8080/h2-console
(Use jdbc:h2:mem:testdb as JDBC URL)

## 📁 Project Structure (High-level)
<pre> ``` src/ └── main/ └── java/ └── com/ └── example/ └── ecomm/ ├── controller/ ├── service/ ├── repository/ ├── model/ └── config/ ``` </pre>

## 💡 Future Scope
- AI-powered product recommendations using OpenAI or Sentence Transformers
- Dockerize the full application for deployment
- Switch to PostgreSQL for production setup

## 🤝 Contributing
Pull requests are welcome! Feel free to fork the repo and submit enhancements, bug fixes, or ideas.

## 📬 Contact
If you want to collaborate or have any questions, reach out via GitHub issues.


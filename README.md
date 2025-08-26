# Student Management System

A backend application built with *Spring Boot* for managing student data.  
This project demonstrates user authentication, student information management, and admin functionalities with proper security and email notifications.  
It also includes *Swagger UI* for API documentation and testing.

---

## 🚀 Features
- *User Signup & Login* with *Spring Security* and *JWT Authentication*
- *Email Notifications* using JavaMailSender (e.g., account creation confirmation)
- *CRUD Operations* for student details (add, update, view)
- *Admin Module* to view all students and generate admission numbers
- *Transaction Management & Indexing* for data consistency and performance
- *Swagger UI Integration* for easy API testing and documentation
- *MongoDB* as the database

---

## 🛠 Tech Stack
- *Java 1.8*
- *Spring Boot*
- *Spring Security & JWT*
- *Spring Data MongoDB*
- *JavaMailSender*
- *Swagger (Swagger UI)*
- *Maven*

---

## 📂 Project Structure

Student-Management-System/
│── src/main/java/com/student/management/system/
│   ├── controller/        # REST Controllers
│   ├── dto/               # Data Transfer Objects
│   ├── entity/            # MongoDB Entities
│   ├── repository/        # Spring Data Repositories
│   ├── security/          # JWT & Spring Security Config
│   ├── service/           # Business Logic
│── src/main/resources/
│   ├── application.yml    # Configuration (DB, Mail, JWT, etc.)
│── pom.xml                # Maven Dependencies


---

## ⚙ Setup & Installation

1. *Clone the Repository*
   bash
   git clone https://github.com/MD-Sohail-dev/student-management-system-backend-project.git
   cd student-management-system
   

2. *Configure application.yml*
   - MongoDB connection details  
   - JWT secret key  
   - Email SMTP settings  


3. *Build and Run*
   bash
   mvn clean install
   mvn spring-boot:run
   

4. *Access Application*
   - API Base URL → http://localhost:8081
   - Swagger UI → http://localhost:8081/swagger-ui.html

---

## 📌 API Endpoints

### Public Controller
- POST /public/signup → Student Signup  
- POST /public/login →  Login  

### Student Controller
- POST /student/add-details
- PUT /student/update → Update personal details  
- GET /student//profile → Get student details  

### Admin Controller
- GET /admin/get-AllStudent → View all student details  
- GET /admin//generate-admission-number → Generate admission number  

---

## 🔐 Security
- Uses *Spring Security* with *JWT* for authentication & authorization.  
- Users must log in to receive a token, which is required for accessing secured endpoints.  

---

## 📧 Email Integration
- On successful signup, a confirmation email is sent using *JavaMailSender*.  
- SMTP configuration must be provided in application.yml.

---

## 📖 Swagger API Docs
- Interactive API documentation is available via *Swagger UI*.  
- Visit:  
  
  http://localhost:8081/swagger-ui.html
  

---

## 👨‍💻 Author
*MD Sohail Ansari*  
- Passionate about Backend Development with Java & Spring Boot  
- [GitHub](https://github.com/MD-Sohail-dev)

---

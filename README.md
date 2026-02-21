cat > README.md << 'EOF'
# Spring Boot Sliding Window Rate Limiter 🚀

## 📌 Overview

This project implements a **Sliding Window Rate Limiter** using Spring Boot.

It limits incoming requests to:

- 5 requests
- Per 10 seconds
- Per IP address
- Returns HTTP 429 Too Many Requests when exceeded

---

## 🏗 Architecture

Client  
↓  
RateLimiterInterceptor  
↓  
RateLimiterService  
↓  
Controller  

---

## ⚙ Technologies Used

- Java
- Spring Boot
- Spring Web
- Maven
- ConcurrentHashMap
- HandlerInterceptor

---

## 🚀 Features

✅ Thread-safe implementation  
✅ Configurable via application.properties  
✅ Sliding Window algorithm  
✅ Rate limiting per IP  
✅ Clean separation of concerns  

---

## 🔧 Configuration

Edit:

src/main/resources/application.properties

Example:

rate.limit.maxRequests=5  
rate.limit.windowMs=10000  

---

## ▶ How To Run

```bash
./mvnw spring-boot:run

# 🛒 E-Commerce Microservices Platform

A production-ready **microservices-based e-commerce backend system** built using **Spring Boot and Spring Cloud**, designed with scalable architecture and containerized deployment support.

---

## 📌 Features

- ✅ Microservices architecture with independent services
- ✅ Service discovery using Netflix Eureka
- ✅ API routing using Spring Cloud Gateway
- ✅ Redis caching for high-performance product retrieval
- ✅ PostgreSQL for persistent data storage
- ✅ Circuit breaker using Resilience4j for fault tolerance
- ✅ Docker-based containerization
- ✅ Kubernetes manifests for deployment orchestration

---

## 🏗️ Architecture Overview

This system follows a **distributed microservices architecture**:

- 🔍 **Discovery Server (Eureka)**  
  Registers and manages all microservices dynamically

- 🌐 **API Gateway**  
  Single entry point for all client requests

- 📦 **Product Service**  
  Handles product catalog and integrates Redis caching

- 🛒 **Order Service**  
  Manages order processing with circuit breaker support

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot 3
- Spring Cloud (Eureka, Gateway)
- PostgreSQL
- Redis
- Resilience4j
- Docker
- Kubernetes

---

## 📁 Project Structure

```
ecommerce-microservices-platform/
├── discovery-server
├── api-gateway
├── product-service
├── order-service
├── docker-compose.yml
└── k8s/
```

---

## 🐳 Running with Docker

```bash
docker compose up --build
```

---

## ☸️ Kubernetes Deployment

```bash
kubectl apply -f k8s/
```

---

## 👨‍💻 Author

Prakash V

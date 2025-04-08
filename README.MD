# StartKit API

StartKit API là một ứng dụng RESTful API được xây dựng bằng **Spring Boot 3.4.2** và **Java 17**, tuân theo kiến trúc **DDD (Domain-Driven Design)**.

## 🛠️ Công nghệ sử dụng

- **Spring Boot 3.4.2**
- **Java 17**
- **Spring Security với OAuth2**
- **HikariCP (Database Connection Pool)**
- **PostgreSQL**
- **Lombok**
- **MapStruct**
- **Spring DevTools**
- **Docker & Docker Compose**

## 💂️ Cấu trúc thư mục

```
com.startkit.api
│── application
│   ├── dto
│   ├── service
│   ├── exception
│── domain
│   ├── model
│   ├── repository
│── infrastructure
│   ├── persistence
│   ├── security
│── interfaces
│   ├── rest
│── config
│── ApiApplication.java
```

## 🚀 Cách chạy dự án

### 1. Chạy bằng Maven

```sh
mvn clean install
mvn spring-boot:run
```

### 2. Chạy bằng Docker

```sh
docker-compose up --build
```

## 🔑 API Authentication

Hệ thống sử dụng **OAuth2 Authorization Code Grant Type**.

### Đăng nhập

- Endpoint: `POST /api/auth/login`
- Request body:
  ```json
  {
    "username": "admin",
    "password": "password123"
  }
  ```

## 📝 Các API chính

| HTTP Method | Endpoint          | Mô tả                    |
| ----------- | ----------------- | ------------------------ |
| `GET`       | `/api/users`      | Lấy danh sách người dùng |
| `GET`       | `/api/users/{id}` | Lấy thông tin chi tiết   |
| `POST`      | `/api/auth/login` | Đăng nhập                |

## 🐚 License

MIT License © 2025 StartKit Team


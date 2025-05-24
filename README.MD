# Clean Architecture và Domain-Driven Design (DDD)

**Clean Architecture** và **Domain-Driven Design (DDD)** là hai kiến trúc phần mềm có thể kết hợp với nhau để tạo ra các hệ thống dễ bảo trì, dễ kiểm thử và phản ánh rõ ràng nghiệp vụ của domain (lĩnh vực kinh doanh).

---

## 1. Clean Architecture là gì?

Clean Architecture là một kiến trúc phần mềm do **Robert C. Martin (Uncle Bob)** đề xuất, nhấn mạnh vào:

- **Tách biệt rõ ràng các tầng (layers)** trong ứng dụng.
- **Hướng domain**: logic nghiệp vụ nằm ở trung tâm, không bị phụ thuộc vào framework, UI hay database.
- **Nguyên tắc Dependency Rule**: Phụ thuộc luôn đi từ ngoài vào trong (từ UI, Infrastructure vào Domain).

### Các tầng trong Clean Architecture:

```
┌───────────────────────┐
│      Outer Layer      │ ← Frameworks & Drivers (Web, DB, UI)
├───────────────────────┤
│     Interface Layer    │ ← Adapters (Controllers, Gateways)
├───────────────────────┤
│  Application/Use Case  │ ← Use Cases (Business logic)
├───────────────────────┤
│      Domain Layer      │ ← Entities (Core Business Rules)
└───────────────────────┘
```

---

## 2. Domain-Driven Design (DDD) là gì?

DDD là một cách tiếp cận thiết kế phần mềm do **Eric Evans** giới thiệu, tập trung vào **domain (nghiệp vụ)**. DDD khuyến khích:

- **Tập trung vào nghiệp vụ** chứ không phải chi tiết kỹ thuật.
- **Ngôn ngữ chung (Ubiquitous Language)**: phát triển phần mềm bằng cùng một ngôn ngữ mà cả dev và domain expert đều hiểu.
- **Phân chia domain thành các Bounded Context** – các vùng độc lập logic trong hệ thống.
- **Mô hình hoá domain** thông qua các khái niệm: Entity, Value Object, Aggregate, Repository, Service, v.v.

---

## 3. Clean Architecture kết hợp với DDD như thế nào?

Khi kết hợp:

| Clean Architecture     | DDD                              |
|------------------------|-----------------------------------|
| Domain Layer           | Entities, Value Objects           |
| Application Layer      | Use Cases, Domain Services        |
| Interface Layer        | Controllers, Presenters, Gateways |
| Infrastructure Layer   | Repositories, DB, External APIs   |

### Ví dụ mô hình trong Spring Boot:

```
src/
├── domain/               → DDD: Entities, ValueObjects
│   └── model/
│   └── service/
│   └── repository/
├── application/          → UseCases (Application Services)
│   └── usecase/
├── interface/            → Web Controllers, DTOs
│   └── web/
├── infrastructure/       → Implementation of Repository, DB, External Systems
│   └── persistence/
│   └── config/
```

---

## 4. Trong thời đại AI có thể viết code, học Clean ArchitectureClean Architecture với DDD để làm gì?
Khi AI như Copilot, ChatGPT, Cursor có thể tự động sinh code CRUD, UI, APIs, bạn có thể nghĩ:

“Mình chỉ cần biết code framework thôi, AI lo phần còn lại.”

✅ Nhưng AI hiện nay vẫn cần người chỉ đạo — và DDD chính là một lợi thế chiến lược, bởi:
| Lợi thế                           | Giải thích                                                                                                           |
| --------------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| 💡 **Hiểu sâu nghiệp vụ**         | DDD giúp bạn *tư duy theo nghiệp vụ*, điều mà AI chưa thể hiểu rõ ngữ cảnh như con người.                            |
| 🧭 **Thiết kế kiến trúc rõ ràng** | DDD hướng bạn đến hệ thống dễ bảo trì, mở rộng, AI có thể viết code nhưng bạn thiết kế bức tranh tổng thể.           |
| 🧠 **Trở thành domain architect** | Người nắm domain và định hình kiến trúc logic là người quyết định AI cần làm gì.                                     |
| 🛠️ **Hướng dẫn AI hiệu quả**     | Khi bạn biết DDD, bạn có thể yêu cầu AI sinh code cho từng layer: Entity, Service, Aggregate... theo cách chuẩn mực. |
| 🔍 **Giảm technical debt**        | Kiến trúc rõ ràng giảm bug, giảm chi phí bảo trì — điều mà AI-generated code chưa đảm bảo được.                      |

## 5. Example với Clean Architecture + DDD (bằng project này)

Dưới đây là ví dụ thực tế về cách project này áp dụng Clean Architecture kết hợp DDD:

```
src/
├── domain/               → Chứa logic nghiệp vụ cốt lõi (Entities, Model)
│   └── model/            → Định nghĩa Entity (User, ...)
│   └── dao/              → Giao diện truy xuất dữ liệu domain
├── application/          → Chứa các service nghiệp vụ, tách biệt với tầng domain
│   └── service/          → Service nghiệp vụ (MessageService, ...)
│   └── service/command/  → Xử lý các command (ghi dữ liệu)
│   └── service/query/    → Xử lý các query (đọc dữ liệu)
├── interfaces/           → Tầng giao tiếp bên ngoài (REST API, DTO)
│   └── rest/             → Controller REST (UserController, HelloWorldController)
├── infrastructure/       → Kết nối với hệ thống bên ngoài (DB, implement repository)
│   └── entity/           → Entity ánh xạ với DB (UserEntity, ...)
│   └── persistence/dao/  → Triển khai DAO
│   └── persistence/repository/ → Triển khai Repository (UserRepository)
```

### Ví dụ luồng xử lý tạo User:
1. **Controller** (interfaces/rest/UserController) nhận request tạo user.
2. Gọi **Application Service** (application/service/command) để xử lý logic tạo user.
3. Application Service thao tác với **Domain Model** (domain/model/User) và sử dụng **DAO/Repository** (domain/dao, infrastructure/persistence/repository) để lưu xuống DB.
4. **Infrastructure** thực hiện lưu trữ thực tế qua JPA/Hibernate.

#### Lợi ích khi tổ chức theo Clean Architecture + DDD :

- **Tập trung vào nghiệp vụ thay vì chi tiết kỹ thuật:**
  - Toàn bộ logic xử lý nghiệp vụ (ví dụ: kiểm tra email đã tồn tại, validate thông tin User, quy tắc đặt tên,...) đều nằm ở tầng domain/application. Điều này giúp team chỉ cần tập trung giải quyết bài toán nghiệp vụ, không bị phân tâm bởi chi tiết framework, database hay giao tiếp API.
  - **Ví dụ:** Khi tạo User, logic kiểm tra "email đã tồn tại" sẽ nằm ở domain service hoặc application service, không nằm trong controller hay repository.

- **Dễ bảo trì do các lớp được tách biệt rõ ràng:**
  - Khi cần thay đổi quy tắc nghiệp vụ (ví dụ: bổ sung điều kiện User phải trên 18 tuổi), chỉ cần sửa ở domain/application, không ảnh hưởng đến tầng giao tiếp (REST, DB, UI). Mỗi lớp có trách nhiệm riêng biệt, code dễ đọc, dễ debug.
  - **Ví dụ:** Nếu thay đổi cách sinh mã User, chỉ cần sửa ở domain/model/User hoặc application/service/command, không phải sửa controller hay repository.

- **Dễ kiểm thử domain và use case không phụ thuộc framework:**
  - Có thể viết unit test cho domain/service mà không cần khởi động Spring hay kết nối DB, nhờ đó kiểm thử nhanh và hiệu quả.
  - **Ví dụ:** Có thể mock UserDao để test logic tạo User mà không cần truy cập database thực tế.

- **Hệ thống linh hoạt, dễ mở rộng hoặc thay thế công nghệ (DB, UI):**
  - Nếu muốn đổi DB (MySQL → MongoDB) hoặc thêm API (REST → GraphQL), chỉ cần thay đổi ở tầng infrastructure mà không ảnh hưởng đến domain logic.
  - **Ví dụ:** Nếu chuyển từ JPA sang MongoDB, chỉ cần viết lại phần implement UserDao ở infrastructure/persistence/dao mà không phải sửa logic tạo User ở application/service/command.

**Tóm lại:**  
Khi tổ chức theo Clean Architecture + DDD, mỗi thay đổi về nghiệp vụ chỉ tác động đến domain/application, còn thay đổi về công nghệ chỉ tác động đến infrastructure. Điều này giúp hệ thống vừa bền vững về mặt nghiệp vụ, vừa linh hoạt về mặt kỹ thuật.

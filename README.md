# ChillGlasses Backend

ChillGlasses Backend là dịch vụ RESTful hỗ trợ ứng dụng thương mại điện tử Chill Glasses. Đây là thành phần server chịu trách nhiệm về dữ liệu, nghiệp vụ và tích hợp hệ thống: quản lý sản phẩm, giỏ hàng, đơn hàng, thanh toán, xác thực, thông báo và API cho giao diện người dùng (frontend).

## Tóm tắt

- Mục tiêu: cung cấp API rõ ràng, an toàn và dễ mở rộng để phục vụ frontend Next.js.
- Thiết kế: tách biệt `controller → service → repository`, sử dụng DTO cho I/O, dễ test và mở rộng.

## Tính năng chính

- Quản lý sản phẩm & danh mục (CRUD, lọc, phân trang)
- Giỏ hàng và quy trình tạo đơn hàng
- Xử lý thanh toán (mô phỏng / tích hợp gateway)
- Xác thực người dùng bằng JWT, quản lý profile, upload avatar
- Quản trị (Admin): quản lý người dùng, đơn hàng, thống kê
- Đánh giá sản phẩm, thông báo và gửi email

## Kiến trúc & Công nghệ

- Ngôn ngữ & Framework: Java 17+, Spring Boot
- Persistence: Spring Data JPA (MySQL / MariaDB; hỗ trợ H2 cho dev)
- Build: Maven (`./mvnw` có sẵn trong repo)
- Mail: Spring Mail (SMTP)
- Authentication: JWT
- Kiến trúc: RESTful API, phân lớp rõ ràng (controller/service/repository)

## Yêu cầu hệ thống

- Java 17+ (JDK)
- Maven hoặc sử dụng wrapper `./mvnw`
- MySQL / MariaDB (hoặc dùng H2 cho development)

## Cài đặt & Chạy nhanh

1. Sao chép cấu hình mẫu và cấu hình kết nối database / mail:
   - `src/main/resources/application-dev.properties` hoặc `application.properties`
   - Thiết lập `spring.datasource.*`, `spring.mail.*`, `jwt.secret`, v.v.

2. Build & chạy (development):

```bash
cd ChillGlassesBackend
./mvnw clean package
./mvnw spring-boot:run
```

3. Hoặc chạy jar sau khi build:

```bash
java -jar target/ChillGlassesBackend-*.jar
```

## Môi trường phát triển

- Chạy test: `./mvnw test`
- Kiểm tra báo cáo test: `target/surefire-reports`
- Logs: xem console hoặc file logs nếu cấu hình

## Cấu hình môi trường

- Lưu các biến cấu hình nhạy cảm (DB credentials, SMTP, JWT secret) ở file cấu hình dev/production hoặc biến môi trường.
- Tránh commit secrets vào Git.

## Góp phần & Quy trình Git

- Tạo branch từ `develop`: `dev/task-xxx`.
- Commit rõ ràng, nhỏ gọn; mở Pull Request vào `develop` và yêu cầu review trước khi merge.

## Hỗ trợ

Nếu gặp lỗi khi chạy, thu thập stacktrace và mô tả bước tái hiện, rồi tạo issue trên repository kèm log và cấu hình bạn đã dùng.

---

License: MIT

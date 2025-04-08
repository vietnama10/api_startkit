# Sử dụng JDK 17 chính thức từ OpenJDK
FROM eclipse-temurin:17-jdk
WORKDIR /app
RUN apt-get update && apt-get install -y maven
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY . ./
# Local start app
CMD ["mvn", "spring-boot:run"]

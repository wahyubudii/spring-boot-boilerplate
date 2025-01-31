## Dependency
– If you want to use PostgreSQL:
```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```
– or MySQL:
```xml
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>
```
## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.yml`
- For PostgreSQL:
```
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/dbname
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update # Hibernate ddl auto (create, create-drop, validate, update)
    show-sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
  security:
    jwt:
      secret: jwtSecretKey
      expiration: 86400000 # 24 hours
```
- For MySQL
```
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/dbname
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update # Hibernate ddl auto (create, create-drop, validate, update)
    show-sql: true
  security:
    jwt:
      secret: jwtSecretKey
      expiration: 86400000 # 24 hours
```

## Run following SQL insert statements
– multi data
```xml
INSERT INTO roles (id, name) VALUES 
(gen_random_uuid(), 'ROLE_ADMIN'),
(gen_random_uuid(), 'ROLE_USER'),
(gen_random_uuid(), 'ROLE_MODERATOR');
```

– single data
```xml
INSERT INTO roles (id, name) VALUES 
(gen_random_uuid(), 'ROLE_MODERATOR');
```

For more detail, please visit:
> [Secure Spring Boot with Spring Security & JWT Authentication](https://bezkoder.com/spring-boot-jwt-authentication/)

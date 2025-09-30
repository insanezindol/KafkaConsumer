# Kafka Consumer 프로젝트

## 개요

이 프로젝트는 Spring Boot 기반의 Kafka Consumer 애플리케이션입니다. Kafka에서 메시지를 수신하여 Elasticsearch와 MySQL 데이터베이스에 저장하는 기능을 제공합니다.

## 기술 스택

-   **Java**: 1.8
-   **Spring Boot**: 2.1.2.RELEASE
-   **Spring Kafka**: Kafka 메시지 처리
-   **Elasticsearch**: 메시지 검색 및 저장
-   **MySQL**: 관계형 데이터베이스
-   **MyBatis**: ORM 프레임워크
-   **Jasypt**: 설정 정보 암호화
-   **Lombok**: 코드 간소화

## 프로젝트 구조

```
src/
├── main/
│   ├── java/kr/co/lunasoft/
│   │   ├── KafkaConsumerApplication.java    # 메인 애플리케이션
│   │   ├── config/                          # 설정 클래스
│   │   │   ├── DefaultDatabaseConfig.java   # 데이터베이스 설정
│   │   │   └── JasyptConfig.java           # 암호화 설정
│   │   ├── listener/
│   │   │   └── KafkaConsumer.java          # Kafka 메시지 리스너
│   │   ├── mapper/
│   │   │   └── MysqlMapper.java            # MyBatis 매퍼 인터페이스
│   │   ├── model/                          # 데이터 모델
│   │   │   ├── ChildInfo.java
│   │   │   ├── GeoPoint.java
│   │   │   ├── MessageInfo.java
│   │   │   └── NoticeInfo.java
│   │   ├── service/                        # 서비스 레이어
│   │   │   ├── ElasticsearchService.java
│   │   │   └── MysqlService.java
│   │   └── util/
│   │       └── DateUtil.java               # 날짜 유틸리티
│   └── resources/
│       ├── application.yml                 # 애플리케이션 설정
│       ├── mapper/
│       │   └── MysqlMapper.xml            # MyBatis SQL 매핑
│       └── logback.xml                    # 로깅 설정
└── test/
    └── java/kr/co/lunasoft/
        └── KafkaConsumerApplicationTests.java
```

## 주요 기능

### Kafka Topic 리스너

애플리케이션은 다음 Kafka 토픽들을 수신합니다:

1. **`elastic-call`**: Elasticsearch에 메시지 저장

    - `MessageInfo` 객체로 변환하여 처리
    - 요청 시간을 현재 시간으로 설정
    - Elasticsearch의 `lunasoft-message/message` 인덱스에 저장

2. **`mysql-call`**: MySQL 데이터베이스에 공지사항 저장

    - `NoticeInfo` 객체로 변환하여 처리
    - MySQL 테이블에 공지사항 데이터 삽입

3. **`test`**: 테스트용 토픽 (로그 출력만)

4. **`dean`**: 개발자용 토픽 (로그 출력만)

### 데이터 저장소

-   **Elasticsearch**: 메시지 검색 및 분석을 위한 저장소
-   **MySQL**: 구조화된 데이터 저장을 위한 관계형 데이터베이스

## 설정

### application.yml

주요 설정 항목:

```yaml
spring:
    kafka:
        consumer:
            group-id: kafka-call-group
            auto-offset-reset: latest
        bootstrap-servers: ubuntu-server:9092
    datasource:
        url: jdbc:log4jdbc:mysql://ubuntu-server:3306/appdb
```

### 보안

-   Jasypt를 사용하여 데이터베이스 사용자명과 비밀번호 암호화
-   암호화된 설정값은 `ENC()` 형태로 저장

## 빌드 및 실행

### 필요 조건

-   Java 1.8 이상
-   Maven 3.6 이상
-   Kafka 서버 (ubuntu-server:9092)
-   MySQL 서버 (ubuntu-server:3306)
-   Elasticsearch 서버

### 빌드

```bash
./mvnw clean package
```

### 실행

```bash
./mvnw spring-boot:run
```

또는 JAR 파일로 실행:

```bash
java -jar target/KafkaConsumer-0.0.1-SNAPSHOT.jar
```

## 환경 설정

### 1. Kafka 서버 설정

Kafka 브로커가 `ubuntu-server:9092`에서 실행되고 있어야 합니다.

### 2. MySQL 데이터베이스 설정

-   서버: `ubuntu-server:3306`
-   데이터베이스: `appdb`
-   테이블 스키마는 `MysqlMapper.xml` 참조

### 3. Elasticsearch 설정

Elasticsearch 클러스터가 설정되어 있어야 하며, `lunasoft-message` 인덱스가 생성되어야 합니다.

## 로깅

-   SLF4J와 Logback을 사용한 로깅
-   SQL 쿼리 로깅을 위한 log4jdbc 사용
-   로그 설정은 `logback.xml`에서 관리

## 모니터링

Spring Boot Actuator를 통한 애플리케이션 상태 모니터링 지원

## 개발자 정보

-   **조직**: kr.co.lunasoft
-   **프로젝트**: KafkaConsumer
-   **버전**: 0.0.1-SNAPSHOT

## 라이센스

이 프로젝트의 라이센스 정보는 별도로 명시되지 않았습니다.

# Hexagonal Architecture - 숙박 예약 시스템

## 프로젝트 소개

**Hexagonal Architecture (육각형 아키텍처)** 또는 **Ports and Adapters** 패턴을 적용한 숙박 예약 시스템입니다.
이 프로젝트는 Layered Architecture와의 비교를 통해 각 아키텍처 패턴의 장단점을 학습하기 위한 목적으로 개발되었습니다.

비즈니스 로직을 외부 기술로부터 완전히 격리하여, 테스트 용이성과 유지보수성을 극대화하는 것이 핵심 목표입니다.

## 주요 기능

### 1. 숙소 관리 (Lodging)

- 숙소 등록
- 전체 숙소 조회
- 특정 숙소 조회
- 숙소 가용성 업데이트

### 2. 예약 관리 (Reservation)

- 예약 생성
- 사용자별 예약 조회
- 예약 확인
- 예약 취소
- 예약 날짜 중복 검증

### 3. 사용자 관리 (User)

- 사용자 등록
- 사용자 조회

## 아키텍처

### Hexagonal Architecture 패턴

```
                    ┌─────────────────────────┐
                    │                         │
        ┌───────────│   Web Adapter (IN)      │◄────── HTTP
        │           │   Controllers           │
        │           │                         │
        │           └─────────────────────────┘
        │                      │
        │                      ▼
        │           ┌─────────────────────────┐
        │           │   Input Ports           │
        │           │   (Use Cases)           │
        │           └─────────────────────────┘
        │                      │
        │                      ▼
        │           ┌─────────────────────────┐
        │           │                         │
        └──────────►│   Application Core      │
                    │   (Business Logic)      │
        ┌──────────►│   Domain Model          │
        │           │                         │
        │           └─────────────────────────┘
        │                      │
        │                      ▼
        │           ┌─────────────────────────┐
        │           │   Output Ports          │
        │           │   (Interfaces)          │
        │           └─────────────────────────┘
        │                      │
        │                      ▼
        │           ┌─────────────────────────┐
        │           │                         │
        └───────────│   Persistence Adapter   │──────► Database
                    │   (OUT)                 │
                    │                         │
                    └─────────────────────────┘
```

### 핵심 원칙

1. **의존성 역전 (Dependency Inversion)**

   - 모든 의존성이 도메인(내부)을 향함
   - 외부 기술이 비즈니스 로직에 의존

2. **포트와 어댑터 (Ports & Adapters)**

   - **Port**: 비즈니스 로직과 외부의 계약 (인터페이스)
   - **Adapter**: 포트를 구현하는 구체적인 기술

3. **비즈니스 로직 격리**
   - 도메인 로직은 프레임워크와 독립적
   - 외부 변경이 내부에 영향을 주지 않음

### 장점

- **높은 테스트 용이성**: 비즈니스 로직을 독립적으로 테스트
- **기술 독립성**: 프레임워크, DB 교체가 용이
- **명확한 경계**: 포트를 통한 명시적 계약
- **유연성**: 새로운 어댑터 추가가 쉬움
- **유지보수성**: 변경의 영향 범위가 제한적

### 단점

- **높은 초기 복잡도**: 더 많은 파일과 인터페이스
- **학습 곡선**: 개념 이해에 시간 필요
- **보일러플레이트**: 인터페이스와 구현체 분리로 코드량 증가
- **과도한 추상화**: 작은 프로젝트에는 오버엔지니어링

## 폴더 구조

```
hexagonal/
├── src/main/java/cori1304/hexagonal/
│   ├── HexagonalApplication.java        # Spring Boot 애플리케이션 진입점
│   │
│   ├── lodging/                         # Lodging 도메인 (16 files)
│   │   │
│   │   ├── domain/                      # 도메인 모델 (7 files)
│   │   │   ├── model/
│   │   │   │   └── Lodging.java         # 도메인 엔티티
│   │   │   └── port/
│   │   │       ├── in/                  # Input Ports (4 files)
│   │   │       │   ├── LodgingCommandPort.java
│   │   │       │   └── LodgingQueryPort.java
│   │   │       └── out/                 # Output Ports (2 files)
│   │   │           ├── LoadLodgingPort.java
│   │   │           └── SaveLodgingPort.java
│   │   │
│   │   ├── application/                 # 애플리케이션 계층
│   │   │   ├── port/
│   │   │   │   ├── in/                  # Use Case 인터페이스 (3 files)
│   │   │   │   │   ├── CreateLodgingUseCase.java
│   │   │   │   │   ├── GetLodgingUseCase.java
│   │   │   │   │   └── UpdateLodgingAvailabilityUseCase.java
│   │   │   │   └── out/                 # Repository 인터페이스 (2 files)
│   │   │   │       ├── LoadLodgingPort.java
│   │   │   │       └── SaveLodgingPort.java
│   │   │   └── service/
│   │   │       └── LodgingService.java  # Use Case 구현체
│   │   │
│   │   └── adapter/                     # 어댑터 계층
│   │       ├── in/                      # Inbound Adapters (3 files)
│   │       │   └── web/
│   │       │       ├── LodgingController.java      # REST API
│   │       │       ├── CreateLodgingRequest.java   # DTO
│   │       │       └── LodgingResponse.java        # DTO
│   │       └── out/                     # Outbound Adapters (4 files)
│   │           └── persistence/
│   │               ├── LodgingJpaEntity.java       # JPA 엔티티
│   │               ├── LodgingMapper.java          # 도메인 ↔ JPA 변환
│   │               ├── LodgingPersistenceAdapter.java
│   │               └── SpringDataLodgingRepository.java
│   │
│   ├── reservation/                     # Reservation 도메인
│   │   ├── domain/
│   │   │   ├── Reservation.java
│   │   │   └── ReservationStatus.java
│   │   ├── application/
│   │   │   ├── port/in/
│   │   │   │   ├── CreateReservationUseCase.java
│   │   │   │   ├── GetReservationUseCase.java
│   │   │   │   ├── ManageReservationUseCase.java
│   │   │   │   └── CreateReservationCommand.java
│   │   │   ├── port/out/
│   │   │   │   ├── LoadReservationPort.java
│   │   │   │   └── SaveReservationPort.java
│   │   │   └── service/
│   │   │       └── ReservationService.java
│   │   └── adapter/
│   │       ├── in/web/
│   │       │   ├── ReservationController.java
│   │       │   ├── CreateReservationRequest.java
│   │       │   └── ReservationResponse.java
│   │       └── out/persistence/
│   │           ├── ReservationJpaEntity.java
│   │           ├── ReservationMapper.java
│   │           ├── ReservationPersistenceAdapter.java
│   │           └── SpringDataReservationRepository.java
│   │
│   └── user/                            # User 도메인
│       ├── domain/
│       ├── application/
│       └── adapter/
│
├── build.gradle                         # Gradle 빌드 설정
└── README.md                            # 프로젝트 문서
```

### 패키지 구조 설명

#### 1. Domain Layer (도메인 계층)

- **역할**: 비즈니스 규칙과 엔티티 정의
- **특징**: 외부 의존성 없음, 순수 Java
- **구성**: 도메인 모델, 포트 인터페이스

#### 2. Application Layer (애플리케이션 계층)

- **역할**: Use Case 구현, 비즈니스 로직 조율
- **특징**: 포트를 통해 어댑터와 통신
- **구성**: Service, Use Case 인터페이스

#### 3. Adapter Layer (어댑터 계층)

- **역할**: 외부 기술과의 연결
- **특징**: 포트 구현, 기술 종속적
- **구성**:
  - **Inbound**: Web Controller, CLI 등
  - **Outbound**: Database, External API 등

## 코드 복잡도

### 전체 통계

- **총 파일 수**: 29개+
- **총 코드 라인**: ~600줄+ (주석 제외)

### 도메인별 분석 (Lodging 기준)

| 계층                    | 파일 수  | 라인 수    | 역할                  |
| ----------------------- | -------- | ---------- | --------------------- |
| **Domain Model**        | 1개      | ~40줄      | 비즈니스 엔티티       |
| **Input Ports**         | 3개      | ~40줄      | Use Case 인터페이스   |
| **Output Ports**        | 2개      | ~15줄      | Repository 인터페이스 |
| **Service**             | 1개      | ~47줄      | Use Case 구현         |
| **Web Adapter**         | 3개      | ~96줄      | Controller + DTOs     |
| **Persistence Adapter** | 4개      | ~120줄     | JPA 구현              |
| **합계**                | **14개** | **~358줄** |                       |

### 계층별 복잡도

#### 1. Domain Layer

- **파일 수**: 7개 (모델 1 + 포트 6)
- **평균 라인 수**: ~15줄/파일
- **복잡도**: 낮음 (인터페이스 위주)

#### 2. Application Layer

- **파일 수**: 6개 (포트 5 + 서비스 1)
- **평균 라인 수**: ~20줄/파일
- **복잡도**: 중간 (비즈니스 로직)

#### 3. Adapter Layer

- **파일 수**: 7개 (Web 3 + Persistence 4)
- **평균 라인 수**: ~30줄/파일
- **복잡도**: 중간 (기술 통합)

### Layered vs Hexagonal 비교

| 항목          | Layered | Hexagonal | 차이  |
| ------------- | ------- | --------- | ----- |
| 총 파일 수    | 14개    | 29개+     | +107% |
| Lodging 파일  | 4개     | 14개      | +250% |
| Lodging 코드  | ~118줄  | ~358줄    | +203% |
| 인터페이스 수 | 3개     | 11개      | +267% |
| 추상화 수준   | 낮음    | 높음      | -     |
| 테스트 격리   | 어려움  | 쉬움      | -     |

### 복잡도 증가 원인

1. **명시적 포트**: 5개의 Use Case/Repository 인터페이스
2. **어댑터 분리**: Web과 Persistence 어댑터 각각 구현
3. **DTO 분리**: Request/Response 객체 별도 관리
4. **매퍼 클래스**: 도메인 ↔ JPA 엔티티 변환
5. **계층 격리**: 각 계층이 독립적인 모델 사용

## 기술 스택

- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **ORM**: Spring Data JPA
- **Database**: H2 (In-memory)
- **Build Tool**: Gradle
- **Libraries**: Lombok, Spring Validation

## 실행 방법

### 1. 프로젝트 클론

```bash
cd hexagonal
```

### 2. 빌드

```bash
./gradlew build
```

### 3. 실행

```bash
./gradlew bootRun
```

### 4. API 테스트

```bash
# 숙소 조회
curl http://localhost:8080/api/lodgings

# 숙소 생성
curl -X POST http://localhost:8080/api/lodgings \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Cozy Apartment",
    "description": "Beautiful apartment in the city center",
    "maxGuests": 4,
    "available": true
  }'

# 가용성 업데이트
curl -X PATCH "http://localhost:8080/api/lodgings/1/availability?available=false"
```

## API 엔드포인트

### Lodging (숙소)

- `GET /api/lodgings` - 전체 숙소 조회
- `GET /api/lodgings/{id}` - 특정 숙소 조회
- `POST /api/lodgings` - 숙소 생성
- `PATCH /api/lodgings/{id}/availability` - 가용성 업데이트

### Reservation (예약)

- `GET /api/reservations/user/{userId}` - 사용자별 예약 조회
- `GET /api/reservations/{id}` - 특정 예약 조회
- `POST /api/reservations` - 예약 생성
- `PUT /api/reservations/{id}/confirm` - 예약 확인
- `PUT /api/reservations/{id}/cancel` - 예약 취소

### User (사용자)

- `GET /api/users` - 전체 사용자 조회
- `GET /api/users/{id}` - 특정 사용자 조회
- `POST /api/users` - 사용자 생성

## 핵심 설계 패턴

### 1. Dependency Inversion Principle (DIP)

```java
// Application이 인터페이스(Port)에 의존
public class LodgingService implements CreateLodgingUseCase {
    private final SaveLodgingPort saveLodgingPort;  // 인터페이스
    // ...
}

// Adapter가 Port를 구현
@Component
public class LodgingPersistenceAdapter implements SaveLodgingPort {
    // 구체적인 JPA 구현
}
```

### 2. Use Case Pattern

```java
// 각 비즈니스 기능을 명시적인 인터페이스로 정의
public interface CreateLodgingUseCase {
    Lodging createLodging(Lodging lodging);
}

public interface UpdateLodgingAvailabilityUseCase {
    Lodging updateLodgingAvailability(Long id, boolean isAvailable);
}
```

### 3. Mapper Pattern

```java
// 도메인 모델과 JPA 엔티티 분리
public class LodgingMapper {
    public static LodgingJpaEntity toJpaEntity(Lodging domain) { }
    public static Lodging toDomain(LodgingJpaEntity entity) { }
}
```

## 비교 프로젝트

동일한 기능을 **Layered Architecture**로 구현한 프로젝트가 `../layered/` 디렉토리에 있습니다.
두 프로젝트를 비교하여 각 아키텍처 패턴의 특징을 학습할 수 있습니다.

### 주요 차이점

- **파일 수**: Hexagonal (29개+) vs Layered (14개)
- **코드량**: Hexagonal (~600줄+) vs Layered (~300줄)
- **복잡도**: Hexagonal (높음) vs Layered (낮음)
- **유연성**: Hexagonal (높음) vs Layered (보통)

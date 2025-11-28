# Layered Architecture - 숙박 예약 시스템

## 프로젝트 소개

전통적인 **Layered Architecture (계층형 아키텍처)** 패턴을 적용한 숙박 예약 시스템입니다.
이 프로젝트는 Hexagonal Architecture와의 비교를 통해 각 아키텍처 패턴의 장단점을 학습하기 위한 목적으로 개발되었습니다.

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

### Layered Architecture 패턴

```
┌─────────────────────────────────┐
│   Presentation Layer            │  ← interfaces/
│   (Controllers)                 │
└─────────────────────────────────┘
            ↓
┌─────────────────────────────────┐
│   Application Layer             │  ← application/
│   (Services)                    │
└─────────────────────────────────┘
            ↓
┌─────────────────────────────────┐
│   Domain Layer                  │  ← domain/
│   (Entities, Business Logic)    │
└─────────────────────────────────┘
            ↓
┌─────────────────────────────────┐
│   Infrastructure Layer          │  ← infrastructure/
│   (Repositories, DB)            │
└─────────────────────────────────┘
```

### 특징

- **단순성**: 직관적이고 이해하기 쉬운 구조
- **상위 레이어 → 하위 레이어**: 단방향 의존성
- **빠른 개발**: 적은 보일러플레이트 코드
- **전통적 접근**: Spring Boot의 기본 구조와 유사

### 장점

- 학습 곡선이 낮음
- 빠른 프로토타이핑 가능
- 코드량이 적음
- 팀원 간 이해도가 높음

### 단점

- 계층 간 경계가 모호할 수 있음
- 하위 레이어 변경 시 상위 레이어 영향
- 테스트 격리가 어려울 수 있음
- 비즈니스 로직이 여러 계층에 분산될 수 있음

## 폴더 구조

```
layered/
├── src/main/java/cori1304/layered/
│   ├── LayeredApplication.java          # Spring Boot 애플리케이션 진입점
│   │
│   ├── interfaces/                      # Presentation Layer (3 files)
│   │   ├── LodgingController.java       # 숙소 REST API
│   │   ├── ReservationController.java   # 예약 REST API
│   │   └── UserController.java          # 사용자 REST API
│   │
│   ├── application/                     # Application Layer (3 files)
│   │   ├── LodgingService.java          # 숙소 비즈니스 로직
│   │   ├── ReservationService.java      # 예약 비즈니스 로직
│   │   └── UserService.java             # 사용자 비즈니스 로직
│   │
│   ├── domain/                          # Domain Layer (4 files)
│   │   ├── Lodging.java                 # 숙소 엔티티
│   │   ├── Reservation.java             # 예약 엔티티
│   │   ├── ReservationStatus.java       # 예약 상태 Enum
│   │   └── User.java                    # 사용자 엔티티
│   │
│   └── infrastructure/                  # Infrastructure Layer (3 files)
│       ├── LodgingRepository.java       # 숙소 데이터 접근
│       ├── ReservationRepository.java   # 예약 데이터 접근
│       └── UserRepository.java          # 사용자 데이터 접근
│
├── docs/                                # 문서
│   ├── architecture-comparison.md       # 아키텍처 비교 분석
│   ├── complexity-analysis.md           # 복잡도 분석
│   └── payment-implementation-plan.md   # 결제 기능 구현 계획
│
├── build.gradle                         # Gradle 빌드 설정
└── README.md                            # 프로젝트 문서
```

## 코드 복잡도

### 전체 통계

- **총 파일 수**: 14개
- **총 코드 라인**: ~300줄 (주석 제외)

### 계층별 분석

#### 1. Presentation Layer (interfaces/)

- **파일 수**: 3개
- **평균 라인 수**: ~38줄/파일
- **역할**: HTTP 요청/응답 처리, 라우팅

#### 2. Application Layer (application/)

- **파일 수**: 3개
- **평균 라인 수**: ~50줄/파일
- **역할**: 비즈니스 로직 조율, 트랜잭션 관리

#### 3. Domain Layer (domain/)

- **파일 수**: 4개
- **평균 라인 수**: ~30줄/파일
- **역할**: 핵심 비즈니스 규칙, 엔티티 정의

#### 4. Infrastructure Layer (infrastructure/)

- **파일 수**: 3개
- **평균 라인 수**: ~10줄/파일
- **역할**: 데이터 영속성, 외부 시스템 연동

### 기능별 코드 복잡도 (Lodging 기준)

| 항목          | 라인 수    | 파일 수 |
| ------------- | ---------- | ------- |
| Domain Model  | ~33줄      | 1개     |
| Service Logic | ~37줄      | 1개     |
| Controller    | ~38줄      | 1개     |
| Repository    | ~10줄      | 1개     |
| **합계**      | **~118줄** | **4개** |

## 기술 스택

- **Framework**: Spring Boot 3.5.7
- **Language**: Java 17
- **ORM**: Spring Data JPA
- **Database**: MySQL (Production), H2 (Test)
- **Build Tool**: Gradle
- **Libraries**: Lombok, Spring Boot DevTools

## 실행 방법

### 1. 프로젝트 클론

```bash
cd layered
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
    "address": "Seoul, Korea",
    "description": "Beautiful apartment in the city center",
    "pricePerNight": 100000,
    "maxGuests": 4
  }'
```

## API 엔드포인트

### Lodging (숙소)

- `GET /api/lodgings` - 전체 숙소 조회
- `GET /api/lodgings/{id}` - 특정 숙소 조회
- `POST /api/lodgings` - 숙소 생성
- `PUT /api/lodgings/{id}/availability` - 가용성 업데이트

### Reservation (예약)

- `GET /api/reservations/user/{userId}` - 사용자별 예약 조회
- `POST /api/reservations` - 예약 생성
- `PUT /api/reservations/{id}/confirm` - 예약 확인
- `PUT /api/reservations/{id}/cancel` - 예약 취소

### User (사용자)

- `GET /api/users` - 전체 사용자 조회
- `GET /api/users/{id}` - 특정 사용자 조회
- `POST /api/users` - 사용자 생성

## 비교 프로젝트

동일한 기능을 **Hexagonal Architecture**로 구현한 프로젝트가 `../hexagonal/` 디렉토리에 있습니다.
두 프로젝트를 비교하여 각 아키텍처 패턴의 특징을 학습할 수 있습니다.

### 주요 차이점

- **파일 수**: Layered (14개) vs Hexagonal (29개+)
- **코드량**: Layered (~300줄) vs Hexagonal (~600줄+)
- **복잡도**: Layered (낮음) vs Hexagonal (높음)
- **유연성**: Layered (보통) vs Hexagonal (높음)

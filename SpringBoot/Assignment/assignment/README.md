# QueryDSL 단일 엔티티 검색 API 과제

원본 프로젝트의 `Member.class` 컨벤션을 참고해 `Member` 단일 엔티티의 등록·검색 API를 구현합니다.
연관 엔티티는 사용하지 않으며, Controller → Service → Repository 3계층을 반드시 지킵니다.

## 1. 회원 등록

- `POST /members`
- 요청 본문: `{"name":"Kim Min","age":20}`
- 응답: 저장된 회원의 `id`, `name`, `age`

## 2. 회원 검색

- `GET /members`
- 선택 쿼리 파라미터
  - `name`: 이름 부분 일치, 대소문자 무시
  - `ageGoe`: 최소 나이(이상)
  - `ageLoe`: 최대 나이(이하)
  - `sort`: `ageAsc`, `ageDesc`, `nameAsc`
- 전달된 조건만 AND로 조합한다.
- 나이 범위가 올바르지 않으면 빈 목록을 반환한다.
- 기본 정렬은 나이 오름차순이며, 모든 정렬에 ID 오름차순을 추가한다.
- QueryDSL 동적 `where`와 `null`을 반환하는 조건 보조 메서드를 사용한다.

## 패키지 구조

```
com.example.querydslassignment
├── controller  # HTTP 요청/응답만 담당, Service 의존
├── service     # 유스케이스와 트랜잭션 담당, Repository 의존
├── repository  # JPA 및 QueryDSL 조회 담당
├── entity      # JPA Entity
├── dto         # API 요청/응답 객체
└── config
```

## 실행

저장소 루트에서 실행합니다.

```bash
./gradlew -p assignment bootRun
```

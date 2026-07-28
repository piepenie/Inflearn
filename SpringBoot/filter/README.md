# OncePerRequestFilter 초보자 예제

이 프로젝트는 필터가 실행되는 순서만 배우는 아주 작은 예제입니다.

## 1. 실행하기

```bash
./gradlew bootRun
```

서버가 실행되면 브라우저에서 아래 주소를 엽니다.

```text
http://localhost:8080/hello
```

화면에는 다음 글자가 나타납니다.

```text
Hello!
```

## 2. 로그 확인하기

요청을 한 번 보내면 실행 창에 다음 순서로 로그가 출력됩니다.

```text
1. 첫 번째 필터 시작
2. 두 번째 필터 시작
3. 컨트롤러 실행
4. 두 번째 필터 끝
5. 첫 번째 필터 끝
```

흐름을 그림으로 보면 다음과 같습니다.

```text
요청
 ↓
첫 번째 필터 시작
 ↓
두 번째 필터 시작
 ↓
컨트롤러
 ↓
두 번째 필터 끝
 ↓
첫 번째 필터 끝
 ↓
응답
```

## 3. 코드에서 볼 것

### `OncePerRequestFilter`

두 필터는 다음 클래스를 상속합니다.

```java
public class FirstFilter extends OncePerRequestFilter
```

`OncePerRequestFilter`를 사용하면 한 번의 기본 요청 처리 안에서 같은 필터의 핵심 코드가
중복 실행되는 것을 막을 수 있습니다.

### `doFilterInternal()`

필터에서 실제로 실행할 코드는 이 메서드 안에 작성합니다.

```java
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
)
```

처음에는 매개변수 세 개를 다음 정도로만 이해하면 됩니다.

- `request`: 들어온 요청
- `response`: 나갈 응답
- `filterChain`: 다음 필터로 이동할 수 있게 해 주는 객체

### `filterChain.doFilter()`

```java
filterChain.doFilter(request, response);
```

이 코드가 실행되면 다음 필터로 이동합니다. 다음 필터가 없으면 컨트롤러로 이동합니다.
이 코드를 호출하지 않으면 다음 필터와 컨트롤러가 실행되지 않습니다.

### `@Order`

```java
@Order(1)
public class FirstFilter { }

@Order(2)
public class SecondFilter { }
```

숫자가 작은 필터가 먼저 실행됩니다.

### `@Slf4j`

```java
@Slf4j
public class FirstFilter {
    log.info("첫 번째 필터 시작");
}
```

`@Slf4j`를 붙이면 `log.info()`를 사용할 수 있습니다. 이 프로젝트에서는 Lombok이
필요한 코드를 자동으로 만들어 줍니다.

## 4. 파일 읽는 순서

1. `FirstFilter.java`
2. `SecondFilter.java`
3. `HelloController.java`

이 세 파일만 먼저 읽으면 됩니다. 테스트와 설정 파일은 나중에 봐도 됩니다.

## 5. 다음에 공부할 내용

이 예제를 이해한 뒤에는 아래 순서로 공부하는 것을 추천합니다.

1. 특정 주소에서만 필터 실행하기
2. 필터에서 요청을 막고 바로 응답하기
3. `Filter`와 `Interceptor`의 차이
4. 로그인 검사 필터 만들기
5. 비동기 요청과 에러 요청에서의 필터 동작

지금 단계에서는 `FilterRegistrationBean`, 비동기 디스패치, 에러 디스패치,
Spring Security 필터 순서는 몰라도 괜찮습니다.

## 테스트 실행

```bash
./gradlew test
```

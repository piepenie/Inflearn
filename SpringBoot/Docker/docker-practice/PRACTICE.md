# Redis Repository · Service 구현

## 1. Hash

- [ ] `src/main/java/com/docker/practice/repository/RedisHashPracticeRepository.java` 생성

```java
package com.docker.practice.repository;

import java.time.Duration;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisHashPracticeRepository {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    private final StringRedisTemplate stringRedisTemplate;

    public void put(String key, String field, String value) {
        stringRedisTemplate.<String, String>opsForHash().put(key, field, value);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
    }

    public String get(String key, String field) {
        return stringRedisTemplate.<String, String>opsForHash().get(key, field);
    }

    public Map<String, String> entries(String key) {
        return stringRedisTemplate.<String, String>opsForHash().entries(key);
    }
}
```

- [ ] `src/main/java/com/docker/practice/service/RedisHashPracticeService.java` 생성

```java
package com.docker.practice.service;

import com.docker.practice.repository.RedisHashPracticeRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisHashPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisHashPracticeRepository redisHashPracticeRepository;

    public void put(String key, String field, String value) {
        redisHashPracticeRepository.put(redisKey(key), field, value);
    }

    public String get(String key, String field) {
        return redisHashPracticeRepository.get(redisKey(key), field);
    }

    public Map<String, String> entries(String key) {
        return redisHashPracticeRepository.entries(redisKey(key));
    }

    private String redisKey(String key) {
        return KEY_PREFIX + key;
    }
}
```

## 2. List

- [ ] `src/main/java/com/docker/practice/repository/RedisListPracticeRepository.java` 생성

```java
package com.docker.practice.repository;

import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisListPracticeRepository {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    private final StringRedisTemplate stringRedisTemplate;

    public Long leftPush(String key, String value) {
        Long size = stringRedisTemplate.opsForList().leftPush(key, value);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
        return size;
    }

    public Long rightPush(String key, String value) {
        Long size = stringRedisTemplate.opsForList().rightPush(key, value);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
        return size;
    }

    public String leftPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    public String rightPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    public List<String> range(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }
}
```

- [ ] `src/main/java/com/docker/practice/service/RedisListPracticeService.java` 생성

```java
package com.docker.practice.service;

import com.docker.practice.repository.RedisListPracticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisListPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisListPracticeRepository redisListPracticeRepository;

    public Long leftPush(String key, String value) {
        return redisListPracticeRepository.leftPush(redisKey(key), value);
    }

    public Long rightPush(String key, String value) {
        return redisListPracticeRepository.rightPush(redisKey(key), value);
    }

    public String leftPop(String key) {
        return redisListPracticeRepository.leftPop(redisKey(key));
    }

    public String rightPop(String key) {
        return redisListPracticeRepository.rightPop(redisKey(key));
    }

    public List<String> range(String key, long start, long end) {
        return redisListPracticeRepository.range(redisKey(key), start, end);
    }

    private String redisKey(String key) {
        return KEY_PREFIX + key;
    }
}
```

## 3. Set

- [ ] `src/main/java/com/docker/practice/repository/RedisSetPracticeRepository.java` 생성

```java
package com.docker.practice.repository;

import java.time.Duration;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisSetPracticeRepository {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    private final StringRedisTemplate stringRedisTemplate;

    public Long add(String key, String member) {
        Long addedCount = stringRedisTemplate.opsForSet().add(key, member);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
        return addedCount;
    }

    public Set<String> members(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    public Boolean isMember(String key, String member) {
        return stringRedisTemplate.opsForSet().isMember(key, member);
    }
}
```

- [ ] `src/main/java/com/docker/practice/service/RedisSetPracticeService.java` 생성

```java
package com.docker.practice.service;

import com.docker.practice.repository.RedisSetPracticeRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSetPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisSetPracticeRepository redisSetPracticeRepository;

    public Long add(String key, String member) {
        return redisSetPracticeRepository.add(redisKey(key), member);
    }

    public Set<String> members(String key) {
        return redisSetPracticeRepository.members(redisKey(key));
    }

    public Boolean isMember(String key, String member) {
        return redisSetPracticeRepository.isMember(redisKey(key), member);
    }

    private String redisKey(String key) {
        return KEY_PREFIX + key;
    }
}
```

## 4. Sorted Set

- [ ] `src/main/java/com/docker/practice/repository/RedisSortedSetPracticeRepository.java` 생성

```java
package com.docker.practice.repository;

import java.time.Duration;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisSortedSetPracticeRepository {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    private final StringRedisTemplate stringRedisTemplate;

    public Boolean add(String key, String member, double score) {
        Boolean added = stringRedisTemplate.opsForZSet().add(key, member, score);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
        return added;
    }

    public Set<String> range(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    public Long reverseRank(String key, String member) {
        return stringRedisTemplate.opsForZSet().reverseRank(key, member);
    }

    public Double score(String key, String member) {
        return stringRedisTemplate.opsForZSet().score(key, member);
    }
}
```

- [ ] `src/main/java/com/docker/practice/service/RedisSortedSetPracticeService.java` 생성

```java
package com.docker.practice.service;

import com.docker.practice.repository.RedisSortedSetPracticeRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSortedSetPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisSortedSetPracticeRepository redisSortedSetPracticeRepository;

    public Boolean add(String key, String member, double score) {
        return redisSortedSetPracticeRepository.add(redisKey(key), member, score);
    }

    public Set<String> range(String key, long start, long end) {
        return redisSortedSetPracticeRepository.range(redisKey(key), start, end);
    }

    public Long reverseRank(String key, String member) {
        return redisSortedSetPracticeRepository.reverseRank(redisKey(key), member);
    }

    public Double score(String key, String member) {
        return redisSortedSetPracticeRepository.score(redisKey(key), member);
    }

    private String redisKey(String key) {
        return KEY_PREFIX + key;
    }
}
```

## 5. String

- [ ] `src/main/java/com/docker/practice/repository/RedisStringPracticeRepository.java` 생성

```java
package com.docker.practice.repository;

import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisStringPracticeRepository {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(10);

    private final StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value, DEFAULT_TTL);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public List<String> multiGet(List<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    public Long increment(String key) {
        Long value = stringRedisTemplate.opsForValue().increment(key);
        stringRedisTemplate.expire(key, DEFAULT_TTL);
        return value;
    }
}
```

- [ ] `src/main/java/com/docker/practice/service/RedisStringPracticeService.java` 생성

```java
package com.docker.practice.service;

import com.docker.practice.repository.RedisStringPracticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisStringPracticeService {

    private static final String KEY_PREFIX = "practice:";

    private final RedisStringPracticeRepository redisStringPracticeRepository;

    public void set(String key, String value) {
        redisStringPracticeRepository.set(redisKey(key), value);
    }

    public String get(String key) {
        return redisStringPracticeRepository.get(redisKey(key));
    }

    public List<String> multiGet(List<String> keys) {
        List<String> redisKeys = keys.stream()
                .map(this::redisKey)
                .toList();
        return redisStringPracticeRepository.multiGet(redisKeys);
    }

    public Long increment(String key) {
        return redisStringPracticeRepository.increment(redisKey(key));
    }

    private String redisKey(String key) {
        return KEY_PREFIX + key;
    }
}
```

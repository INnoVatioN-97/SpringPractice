# 김영한님 자바 ORM 표준 JPA 프로그래밍 기본편

---

**JPQL (Java Persistence Query Language)**
- JPA 가 지원하는 다양한 쿼리 방법중 하나.
- JPA 를 사용하면 개발시 DB 를 중심으로 개발하지 않고 객체를 중심으로 개발한다. 이때 문제는 검색 쿼리를 어떻게 야무지게 작성하냐 인데, 정규화 되어 쪼개진 여러 테이블에서 내가 필요한 데이터를 가져오고자 하면 여러 조건이 포함된 SQL이 필요하다. 이러한 때를 위해 JPA 는 SQL 을 추상화한 JPQL 이라고 하는 객체 지향 쿼리를 제공한다.
- JPQL 은 SQL 과 문법이 비슷하고, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 등을 지원한다.


**JPQL VS SQL**
- 엔티티 객체를 대상으로 쿼리 / SQL : DB Table 을 대상으로 쿼리

***JPQL 예시***
```java
// JPQL 의 검색 예시
String jpql= "select m From Member m where m.name like '%hello%'";
List<Member> result = em.createQuery(jpql, Member.class).getResultList();
```

---

## Annotations

### Entity 에서
**@Temporal**
- Java 의 날짜 타입 (Date, Calendar) 등을 DB 와 매핑할 때 사용.
- 근데 자바8 이후로 생긴 LocalDate, LocalDateTime 자료형을 쓰면 굳이 이 어노테이션을 안붙이고 써도 최신 하이버네에이트에서 지원해준다.




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

---

**@MappedSuperclass**
- 여러 테이블에 동일한 속성(예: 작성일, 수정일,작성자 등)이 필요할 때 사용할 수 있다.
- 이 프로젝트에 있는 BaseEntity 같은 클래스를 하나 만들고 거기에 공통 속성 넣고 getter setter 넣고 얘를 extends
- 그 후 공통 엔티티속성이 담긴 해당 클래스(여기선 BaseEntity)에 **@MappedSuperclass** Annotation 을 달아준다.
- @MappedSuperclass 는 @Entity 가 아니므로 테이블과는 관계가 없고 단순히 여러 엔티티에서 공통으로 사용하는 매핑정보를 모아두는 창고역할이다.
- 참고: @Entity 가 적힌 클래스는 엔티티나 @MappedSuperclass 로 지정한 클래스만 상속 가능하다.
  - 쉽게 말하면, Member 가 BaseEntity 를 상속하듯이 @Entity 클래스는 똑같이 @Entity가 적힌 클래스나 BaseEntity 처럼 @MappedSuperclass 가 붙은 클래스만 extend가 된다는 뜻.
```java
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime lastmodifiedDate;
    
    // ... getter ~ setter 
}
```
- 그러나, 이런 @MappedSuperclass 로 매핑된 클래스는 상속관계 매핑도 아니고, 이 자체가 엔티티가 아니므로 테이블과 따로 매핑이 되지않는다. 그 말은 얘는 그냥 상위클래스로 얘를 상속받는 자식 클래스한테 공통된 속성들에 대한 매핑 정보만 제공해줄 뿐, 얘를 가지고 em.find(BaseEntity)같은 일은 못한다.
- 그래서 얘는 얘 자체로 BaseEntity baseEntity = new ~~~ 이렇게 객체를 만들 일이 없으므로 abstract class 로 시즈모드 박아두자.

---

**EntityManager.find() vs EntityManager.getReference()**
- EntityManager.find() : 데이터베이스를 통해 실제 엔티티 객체를 조회
- EntityManager.getReference() : 데이터베이스 조회를 미루는 가짜 엔티티 조회(이 메소드로 반환된 객체에 .getClass()를 하면 hibernate proxy 뭐시기가 뜬다.)
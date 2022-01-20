package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * 기본 키 매핑 어노테이션
 * - 사용할 수 있는 어노테이션은 @Id, @GeneratedValue 두가지가 있다.
 * 직접 할당해서 사용시 @Id 만 사용하고, @GeneratedValue 를 사용하면 키값 등을 알아서 할당한다.
 * MySQL 의 Auto_Increment 느낌
 * 크게 4가지가 있다.
 *  IDENTITY (DB 에 위임, MySQL 용)
 *  SEQUENCE (DB Sequence Object 사용, Oracle 용)
 *   - @SequenceGenerator 필요
 *  TABLE (키 생성용 별도의 테이블을 사용. 모든 DB 에서 사용가능하나, 성능 저하 위험)
 *  AUTO (persistence.xml 내 방언에 따라 자동 지정됨.)
 */
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //    private int age;
//
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    /**
//     * @Temporal : README.md 참조
//     */
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//            //아래 둘과 위 둘은 같은 기능.
////    private LocalDate createdDate;
////
////    private LocalDate lastModifiedDate;
//
//    @Lob
//    private String description;

    public Member(){

    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public RoleType getRoleType() {
//        return roleType;
//    }
//
//    public void setRoleType(RoleType roleType) {
//        this.roleType = roleType;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public vo
}

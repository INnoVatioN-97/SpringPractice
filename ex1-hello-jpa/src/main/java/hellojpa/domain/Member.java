package hellojpa.domain;

import hellojpa.domain.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 기본 키 매핑 어노테이션
 * - 사용할 수 있는 어노테이션은 @Id, @GeneratedValue 두가지가 있다.
 * 직접 할당해서 사용시 @Id 만 사용하고, @GeneratedValue 를 사용하면 키값 등을 알아서 할당한다.
 * MySQL 의 Auto_Increment 느낌
 * 크게 4가지가 있다.
 * IDENTITY (DB 에 위임, MySQL 용)
 * SEQUENCE (DB Sequence Object 사용, Oracle 용)
 * - @SequenceGenerator 필요
 * TABLE (키 생성용 별도의 테이블을 사용. 모든 DB 에서 사용가능하나, 성능 저하 위험)
 * AUTO (persistence.xml 내 방언에 따라 자동 지정됨.)
 */
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;


    /**
     * @ManyToOne : Member 입장에서 다대일 이므로 이를 JPA 에 알리는 Annotation
     * @JoinColumn(name = "TEAM_ID ") : "TEAM_ID" 라는 컬럼으로 Join 하그라
     * 이렇게 되면 연관관계 매핑이 됨.
     * Team 클래스에서 보면 List<Member> members 라는 리스트가 있는데,
     * 그 리스트와 매핑되는 객체이다.
     */
    /**
     * fetch=LAZY 를 주면 team 객체를 프록시 타입으로 준다.
     * fetch=EAGER 를 주면 아예 join 을 해서 같이 가져와버림.
     *  -> 얘는 실무에서 쓰면 ㅈ된다고 한다.
     *  -> 반입을 EAGER (즉시 로딩)으로 박아버리면 JPQL 에서 N+1 문제를 일으킨다.
     *  -> @ManyToOne, @OneToOne 은 기본이 즉시 로딩이라 이걸 명시적으로 LAZY 로 바꿔줘야 된다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    @Override
    public String toString() {
        return String.format("id: %d, name: %s", id, userName);
    }
}

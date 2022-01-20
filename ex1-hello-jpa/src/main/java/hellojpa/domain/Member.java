package hellojpa.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String name;
//
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @Override
    public String toString() {
        return String.format("id: %d, name: %s", id, name);
    }

    /**
     *  @ManyToOne : Member 입장에서 다대일 이므로 이를 JPA 에 알리는 Annotation
     *  @JoinColumn(name = "TEAM_ID ") : "TEAM_ID" 라는 컬럼으로 Join 하그라
     *  이렇게 되면 연관관계 매핑이 됨.
     *  Team 클래스에서 보면 List<Member> members 라는 리스트가 있는데,
     *  그 리스트와 매핑되는 객체이다.
     */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) //"TEAM_ID" 라는 컬럼으로 Join 하그라
    private Team team;
    /**
     *
     */
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member" )
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

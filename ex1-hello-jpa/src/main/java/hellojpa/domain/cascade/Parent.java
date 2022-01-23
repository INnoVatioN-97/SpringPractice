package hellojpa.domain.cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 영속성 CASCADE
 */
@Entity
public class Parent {

    @Id @GeneratedValue
    private Long id;

    private String name;

    /**
     * cascase = CascadeType.All : 이새끼를 EntityManager.persist() 하면,
     *  얘한테 딸린 하위 엔티티들도 Cascade 로 같이 persist 된다.
     *  굳이 em.persist(parent) / em.persist(child1) / em.persist(child2) ㅇㅈㄹ 안해도 된다는 것.
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> childList = new ArrayList<>();

    /**
     * 양방향 연관관계 매핑을 위한 메소드
     * @param child
     */
    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }

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

    public List<Child> getChildList() {
        return childList;
    }
}

package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//JPA 가 관리하는 엔티티
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //Primary Key
    private Long id; // data 구분을 위해 시스템이 저장고 구별하는 용도

    private String name;

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
}

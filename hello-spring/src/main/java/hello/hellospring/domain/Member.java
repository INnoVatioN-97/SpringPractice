package hello.hellospring.domain;

public class Member {
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

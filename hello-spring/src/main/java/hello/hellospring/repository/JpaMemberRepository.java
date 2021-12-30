package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    /**
     * JPA 는 EntityManager 를 통해 모든 것이 동작한다.
     */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        /**
         * JPA 에서는 굳이 뭐 sql문 짤 필요도 없이 EntityManeger 객체에 저 값을
         * persist 해주면 insert into ~~ values (~~,~~) 이런 것을 다 짜준다.
         * 아주 편해진다.
         */
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //EntityManager.find 는 추상 메소드로 반환 값이 <T>이다. 매개변수로 들어온 클래스에 맞게 그 형태로 반환을 시킴.
        Member foundMember = em.find(Member.class, id);
        return Optional.ofNullable(foundMember);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList().stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member  m", Member.class).getResultList();
    }
}

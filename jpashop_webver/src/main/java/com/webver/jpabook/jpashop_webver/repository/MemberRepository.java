package com.webver.jpabook.jpashop_webver.repository;

import com.webver.jpabook.jpashop_webver.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 스프링을 쓰기때문에 Component-scan 대상이 되는 @Repository 를 붙여줌으로써
 * 자동으로 Spring Bean 에 등록된다.
 */
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    /**
     * Spring Container 애서 이 어노테이션이 붙은 엔티티매니저를 DI 해줌.
     */
    private final EntityManager em;

    /**
     * 가급적이면 save 후 멤버 객체를 반환하기보다
     * 추후 조회를 위한 id 정도만 반환해주도록 짠다.
     * 커맨드와 쿼리를 분리하는 습관이 사이드이펙트 스노우볼 굴리는걸 막을 수 있다.
     *
     * @param member
     * @return
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * 1건 조회
     *
     * @param id
     * @return
     */
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    /**
     * SQL 과 JPQL 의 차이
     * SQL = 테이블을 대상으로 쿼리
     * JPQL = 엔티티 객체를 대상으로 쿼리
     *
     * @return
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}

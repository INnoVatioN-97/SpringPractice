package com.webver.jpabook.jpashop_webver.repository;

import com.webver.jpabook.jpashop_webver.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 스프링을 쓰기때문에 Component-scan 대상이 되는 @Repository 를 붙여줌으로써
 * 자동으로 Spring Bean 에 등록된다.
 */
@Repository
public class MemberRepository {

    /**
     * Spring Container 애서 이 어노테이션이 붙은 엔티티매니저를 DI 해줌.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 가급적이면 save 후 멤버 객체를 반환하기보다
     * 추후 조회를 위한 id 정도만 반환해주도록 짠다.
     * 커맨드와 쿼리를 분리하는 습관이 사이드이펙트 스노우볼 굴리는걸 막을 수 있다.
     * @param member
     * @return
     */
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    /**
     * 1건 조회
     * @param id
     * @return
     */
    public Member find(Long id) {
        return em.find(Member.class, id);
    }



}

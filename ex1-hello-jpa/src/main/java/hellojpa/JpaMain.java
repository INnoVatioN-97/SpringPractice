package hellojpa;

import hellojpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * 연습용 메인클래스
 *
 * EntityManagerFactory 객체는 하나만 생성해 앱 전체에서 공유해야.
 * EntityManager 는 한번쓰고 버림. 스레드간 공유 X
 * JPA 의 모든 데이터 변경은 Transaction 안에서 실행
 */
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 1건 조회
//            Member foundMember = em.find(Member.class, 1L);
//
//            foundMember.setName("HelloJPA");
//            System.out.println("foundMember.id : "+foundMember.getId());
//            System.out.println("foundMember.name : "+foundMember.getName());
//            em.persist(foundMember);

            /** 목록 조회 (JPQL)
             * select ~ from ~ where ~ "limit a offset b" 를 구현 할때 : setFirstResult(a).setMaxResult(b)
             * JPA 는 SQL 을 추상화한 JPQL 이라는 객체 지향 쿼리 언어를 제공함. 문법은 일반 SQL 과 비슷
             * JPQL: 엔티티 객체를 대상으로 쿼리 / SQL : DB Table 을 대상으로 쿼리
             */

//            List<Member> resultList = em.createQuery("select m from Member m", Member.class).getResultList();
//            for (Member member : resultList) {
//                 System.out.println("member.name = " + member.getName());
//            }
//            Member mem1 = new Member(150L, "A");
//            Member member1 = new Member();
//            member1.setUserName("A");
//
//            Member member2 = new Member();
//            member2.setUserName("B");
//
//            Member member3 = new Member();
//            member3.setUserName("C");
//
//            System.out.println("====================");
//
//            em.persist(member1);
//            em.persist(member2);
//            em.persist(member3);
//
//            System.out.printf("member1 : %d", member1.getId());
//            System.out.printf("member2 : %d", member2.getId());
//            System.out.printf("member3 : %d", member3.getId());
//
//            System.out.println("====================");

            Member member = new Member();
            member.setId(3L);
            member.setUserName("C");

            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error 생김 ㅆㅂ!");
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

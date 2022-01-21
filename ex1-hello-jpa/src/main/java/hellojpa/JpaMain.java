package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Movie;
import hellojpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
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

            Member member = new Member();
            member.setCreatedBy("Youngil Ko");
            member.setCreatedDate(LocalDateTime.now());
            member.setUserName("User 1");
            em.persist(member);

            em.flush();
            em.clear();

            Member foundMember = em.find(Member.class, member.getId());
            System.out.println("foundMember. createdBy:"+foundMember.getCreatedBy() + ", createdDate: "+foundMember.getCreatedDate() + ", name: "+ foundMember.getUserName());

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error 생김!");
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

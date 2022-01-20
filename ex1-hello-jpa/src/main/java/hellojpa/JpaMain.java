package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;

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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member");
            member.setTeam(team);



            em.flush();
            em.clear();

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

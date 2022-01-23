package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;
import hellojpa.domain.cascade.Child;
import hellojpa.domain.cascade.Parent;

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
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void printMemberAndTeam(Member foundMember) {
        String userName = foundMember.getUserName();
        System.out.println("userName = " + userName);

        Team team = foundMember.getTeam();
        System.out.println("team = " + team.getName());
    }
}

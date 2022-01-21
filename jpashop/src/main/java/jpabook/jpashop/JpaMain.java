package jpabook.jpashop;


import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Book book = new Book();
            book.setName("띵품 JAVA 프로그래밍");
            book.setAuthor("이름모름 ㅎㅇ");

            em.persist(book);
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

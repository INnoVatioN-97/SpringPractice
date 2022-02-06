package com.webver.jpabook.jpashop_webver;

import com.webver.jpabook.jpashop_webver.domain.*;
import com.webver.jpabook.jpashop_webver.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 아래의 더미데이터 만들기 위함. 총 주문 2개
 * userA
 * - JPA1 BOOK
 * - JPA2 BOOK
 * <p>
 * userB
 * - SPRING1 BOOK
 * - SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "seoul", "도곡로 203", "19292");
            em.persist(member);

            Book jpaBook1 = createBook("JPA1", 10000, 100);
            em.persist(jpaBook1);

            Book jpaBook2 = createBook("JPA2", 20000, 100);
            em.persist(jpaBook2);

            OrderItem orderItem1 = OrderItem.createOrderItem(jpaBook1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(jpaBook2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        public void dbInit2() {
            Member member = createMember("userB", "goyang", "원당로 101번길 32", "10298");
            em.persist(member);

            Book spring1 = createBook("SPRING1", 20000, 200);
            em.persist(spring1);

            Book spring2 = createBook("SPRING2", 40000, 300);
            em.persist(spring2);

            OrderItem orderItem1 = OrderItem.createOrderItem(spring1, 10000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(spring2, 20000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book jpaBook1 = new Book();
            jpaBook1.setName(name);
            jpaBook1.setPrice(price);
            jpaBook1.setStockQuantity(stockQuantity);
            return jpaBook1;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
    }

}


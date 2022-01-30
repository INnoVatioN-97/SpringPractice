package com.webver.jpabook.jpashop_webver.repository;

import com.webver.jpabook.jpashop_webver.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    /**
     * 주문 검색.
     * 사용자명 / 주문 상태(배송 완료, 취소 등)에 따라 검색결과를 호출해야하므로
     * 별도의 OrderSearch 라는 클래스를 만들어 이를 통해
     * 조건에 맞는 검색 결과를 반환할 수 있도록 한다.
     * @param orderSearch
     * @return
     */
//    public List<Order> findAll(OrderSearch orderSearch) {
//        return em.createQuery("select o from Order o", Order.class)
//                .getResultList();
//    }
}

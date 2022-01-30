package com.webver.jpabook.jpashop_webver.service;

import com.webver.jpabook.jpashop_webver.domain.Delivery;
import com.webver.jpabook.jpashop_webver.domain.Member;
import com.webver.jpabook.jpashop_webver.domain.Order;
import com.webver.jpabook.jpashop_webver.domain.OrderItem;
import com.webver.jpabook.jpashop_webver.domain.item.Item;
import com.webver.jpabook.jpashop_webver.repository.ItemRepository;
import com.webver.jpabook.jpashop_webver.repository.MemberRepository;
import com.webver.jpabook.jpashop_webver.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    /**
     * 주문 서비스 동작 특성상 여러 리포지토리에 의존하게 된다.
     * 검색시 회원정보, 주문 품목의 수량 등과 같은 정보의 필요성 때문.
     */
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회 (해당 회원과 주문 품목)
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        order.cancel();
    }

    /**
     * 검색
     */
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }
}

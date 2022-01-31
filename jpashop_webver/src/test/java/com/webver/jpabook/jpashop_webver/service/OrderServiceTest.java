package com.webver.jpabook.jpashop_webver.service;

import com.webver.jpabook.jpashop_webver.domain.Address;
import com.webver.jpabook.jpashop_webver.domain.Member;
import com.webver.jpabook.jpashop_webver.domain.Order;
import com.webver.jpabook.jpashop_webver.domain.OrderStatus;
import com.webver.jpabook.jpashop_webver.domain.item.Book;
import com.webver.jpabook.jpashop_webver.domain.item.Item;
import com.webver.jpabook.jpashop_webver.exception.NotEnoughStockException.NotEnoughStockException;
import com.webver.jpabook.jpashop_webver.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{

        //given
        Member member = createMember("회원 1");
        Item book = createBook("사골곰탕", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order foundOrder = orderRepository.findOne(orderId);

        assertEquals( OrderStatus.ORDER, foundOrder.getStatus(),"상품 주문시 상태는 ORDER");
        assertEquals(1, foundOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000*orderCount, foundOrder.getTotalPrice(),"주문가격 = 가격 * 수량이다.");
        assertEquals(8, book.getStockQuantity(),"주문 수량만큼 재고가 줄어야 한다.");
    }

    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울", "도곡로", "483-192"));
        em.persist(member);
        return member;
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception{

        //given
        Member member = createMember("회원 1");
        Item item = createBook("사골곰탕", 10000, 10);

        int orderCount = 11;

        //when
        NotEnoughStockException enoughStockException = assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), item.getId(), orderCount));

        //then
//        fail("이게 터지면 안됨. 다시코드짜자.");
        assertEquals("need more stock", enoughStockException.getMessage());


    }

    @Test
    public void 주문취소() throws Exception{

        //given
        Member member = createMember("김개똥");
        Item item = createBook("응애 응애!", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 CANCEL 이다.");
        assertEquals(10, item.getStockQuantity(), "주문 취소된 상품은 재고가 원복되어야한다.");

    }
}
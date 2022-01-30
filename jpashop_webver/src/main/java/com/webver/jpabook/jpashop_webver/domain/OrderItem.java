package com.webver.jpabook.jpashop_webver.domain;


import com.webver.jpabook.jpashop_webver.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;// 주문 당시 가격
    private int count;// 주문 당시 수량

    protected OrderItem() {}

    //== 생성 메소드 ==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        // 주문 수량만큼 해당 상품의 보유수량을 감소시켜야 된다.
        item.removeStock(count);
        return orderItem;
    }
    //== 비즈니스 로직 ==//
    /**
     * 주문 취소시, 수량 원복을 위함.
     */
    public void cancel() {
        getItem().addStock(count);
    }

    //== 조회 로직 ==//

    /**
     * 주문 상품 전체 가격 조회
     * @return
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}

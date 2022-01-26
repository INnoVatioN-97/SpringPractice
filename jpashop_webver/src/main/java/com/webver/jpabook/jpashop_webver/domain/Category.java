package com.webver.jpabook.jpashop_webver.domain;

import com.webver.jpabook.jpashop_webver.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    /**
     * @JoinColumn : 중간 테이블인 category_item 에 있는 category_id
     * inverseJoinColumns = @JoinColumn(name = "item_id")) : 중간테이블인 category_item의 item 쪽으로 들어가는 FK
     */
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();


    /**
     * 카테고리 상위 분류 : 1개의 상위 분류 안에 여러개의 하위분류가 있다
     *  = 하위 분류 입장에서 "내 부모 밑에 개많은 자식들" 이니까 @ManyToOne
     * 카테고리 하위 분류 : 여러개의 하위 분류가 하나의 상위분류를 가지고 있다 = @OneToMany
     *  그 뒤, parent 에 연관관계를 매핑
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}

package com.webver.jpabook.jpashop_webver.repository;

import com.webver.jpabook.jpashop_webver.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    /**
     * 아이템 추가
     * 만약, 매개변수로 넘어온 아이템의 id가 없다 = 완전히 새로 생성된넘이다
     *  = em.persist()를 진행해 알아서 id 값이 generatedValue 로 들어가도록.
     * 그게 아니면 이미 JPA 에 들어온 애구나 라고 판단해 merge 를 진행해 강제 업데이트.
     * @param item
     */
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    /**
     * 한건 조회
     * @param itemId
     * @return
     */
    public Item findOne(Long itemId) {
        return em.find(Item.class, itemId);
    }

    /**
     * 전체 조회
     * @return
     */
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}

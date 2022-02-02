package com.webver.jpabook.jpashop_webver.service;

import com.webver.jpabook.jpashop_webver.domain.item.Book;
import com.webver.jpabook.jpashop_webver.domain.item.Item;
import com.webver.jpabook.jpashop_webver.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 보다 중간에 현타와서 놓침. 변경감지와 병합(merge) 7분 30초부터 듣기
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item item = itemRepository.findOne(itemId);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        // 영속상태의 엔티티 이므로 em.persist, merge 이런걸 호출할 필요가 없다. 알아서 flush() 되고 수정됨.
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}

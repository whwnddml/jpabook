package com.book.jpa.service;

import com.book.jpa.domain.item.Book;
import com.book.jpa.domain.item.Item;
import com.book.jpa.repository.ItemRepository;
import jakarta.persistence.EntityManager;
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
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId , String name, int price, int stockQuantity){

        Book book = (Book) itemRepository.findOne(itemId);
        book.change(name, price, stockQuantity);

        // 변경감지가 일어나기 때문에 아무것도 안해줘도 된다. 트랜잭션이 커밋되는 순간에 db에 자동 반영 됨.

    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

}

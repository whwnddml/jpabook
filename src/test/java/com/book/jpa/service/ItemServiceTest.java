package com.book.jpa.service;


import com.book.jpa.domain.item.Book;
import com.book.jpa.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 상품등록() throws Exception {
        // given
        Book book = new Book();
        book.setName("자바 ORM JPA 프로그래밍");
        book.setPrice(34000);
        book.setAuthor("김영한");
        book.setIsbn("12132124");

        // when
        em.flush();
        itemRepository.save(book);

        // then
        Assert.assertEquals(book, itemRepository.findOne(book.getId()));

    }

}
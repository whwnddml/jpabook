package com.book.jpa;

import com.book.jpa.domain.*;
import com.book.jpa.domain.item.Book;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService{

        private final EntityManager em;

        public void dbInit1(){
            // Member
            Member member = getMember("userA", "서울", "123", "11111");
            em.persist(member);

            // Book
            Book book1 = getBook("JPA1 BOOK", 20000, 100);
            em.persist(book1);

            Book book2 = getBook("JPA2 BOOK", 30000, 200);
            em.persist(book2);
            
            // OrderItem
            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 2);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 30000, 11);

            // Order
            Order order = Order.createOrder(member, getDelivery(member), orderItem1, orderItem2);
            em.persist(order);

        }

        public void dbInit2(){
            // Member
            Member member = getMember("userB", "경기", "123", "11111");
            em.persist(member);

            // Book
            Book book1 = getBook("Spring1 BOOK", 20000, 100);
            em.persist(book1);

            Book book2 = getBook("Spring2 BOOK", 30000, 200);
            em.persist(book2);

            // OrderItem
            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 4);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 30000, 22);

            // Order
            Order order = Order.createOrder(member, getDelivery(member), orderItem1, orderItem2);
            em.persist(order);

        }

        private static Book getBook(String name, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private static Delivery getDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private static Member getMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

    }

}

package com.book.jpa.service;

import com.book.jpa.domain.Delivery;
import com.book.jpa.domain.Member;
import com.book.jpa.domain.Order;
import com.book.jpa.domain.OrderItem;
import com.book.jpa.domain.item.Item;
import com.book.jpa.repository.ItemRepository;
import com.book.jpa.repository.MemberRepository;
import com.book.jpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     * JPA의 강점 : 엔티티의항목을 수정하면 자동으로 업데이트 됨.
     */
    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();;
    }

    /**
     * 주문 검색
     */
//    public List<Order> findeOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }

}

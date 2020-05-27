package com.github.candyacao.service;

import com.github.candyacao.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(Long userID, Long productID, Integer quantity);
    void deleteOrder(Long id);
    Order getOrderByID(Long id);
    List<Order> getOrdersByUserID(Long userID);
}

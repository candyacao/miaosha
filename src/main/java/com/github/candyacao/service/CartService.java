package com.github.candyacao.service;

import com.github.candyacao.model.Cart;

import java.util.List;

public interface CartService {
    void addCart(Long userID, Long productID, int quantity); // 添加购物车
    void deleteCart(Cart cart); // 删除购物车商品
    List<Cart> getCart(Long userID);  //查询购物车商品信息
    void decProductCount(Cart cart); // 减少购物车商品的数量
}

package com.github.candyacao.service.impl;

import com.github.candyacao.exception.GlobleException;
import com.github.candyacao.mapper.CartMapper;
import com.github.candyacao.model.Cart;
import com.github.candyacao.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.candyacao.common.enums.ResultStatus.*;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addCart(Cart cart) {
       Cart temp = cartMapper.getCartByUserAndGood(cart.getUserID(),cart.getProductID());
       int count = -1;
       if(null == temp){
           count = cartMapper.addCart(cart);
       }else {
           count = cartMapper.addProductCount(temp.getId());
       }
       if(count <= 0){
           throw new GlobleException(CART_ADDGOOD_FAIL);
       }
    }

    @Override
    public void deleteCart(Cart cart) {
        int count = cartMapper.deleteCart(cart);
        if(count <= 0){
            throw new GlobleException(CART_DELETEGOOD_FAIL);
        }
    }

    @Override
    public List<Cart> getCart(Long userID) {
        List<Cart> carts = cartMapper.getCarts(userID);
        return carts;
    }

    @Override
    public void decProductCount(Cart cart) {
        int count = cartMapper.decProductCount(cart.getId());
        if(count <= 0){
            throw new GlobleException(CART_DECGOOD_FAIL);
        }
    }
}

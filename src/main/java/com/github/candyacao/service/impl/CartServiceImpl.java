package com.github.candyacao.service.impl;

import com.github.candyacao.exception.GlobleException;
import com.github.candyacao.mapper.CartMapper;
import com.github.candyacao.mapper.ProductMapper;
import com.github.candyacao.mapper.SeckillMapper;
import com.github.candyacao.model.Cart;
import com.github.candyacao.model.ProductSeckill;
import com.github.candyacao.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.github.candyacao.common.enums.ResultStatus.*;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SeckillMapper seckillMapper;

    @Override
    public void addCart(Long userID, Long productID, int quantity) {
       Cart temp = cartMapper.getCartByUserAndGood(userID, productID);
       int count = -1;
       if(null == temp){
           Cart cart = new Cart();
           cart.setUserID(userID);
           cart.setProductID(productID);
           Date date = new Date();
           cart.setCreateDate(date);
           cart.setUpdateDate(date);
           cart.setQuantity(quantity);
           // 需要判断该商品ID是否是秒杀商品，若是秒杀商品价格取秒杀表，否则取商品表
          ProductSeckill seckill = seckillMapper.getSeckillByProductID(productID);
          if(null == seckill){
              cart.setPrice(productMapper.getProductByID(productID).getPrice());
          }else{
              cart.setPrice(seckill.getPrice());
          }
           count = cartMapper.addCart(cart);
       }else {
           count = cartMapper.addProductCount(temp.getId(),quantity);
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

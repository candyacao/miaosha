package com.github.candyacao.service.impl;

import com.github.candyacao.exception.GlobleException;
import com.github.candyacao.mapper.OrderMapper;
import com.github.candyacao.mapper.ProductMapper;
import com.github.candyacao.mapper.SeckillMapper;
import com.github.candyacao.model.Order;
import com.github.candyacao.model.ProductSeckill;
import com.github.candyacao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.github.candyacao.common.enums.ResultStatus.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SeckillMapper seckillMapper;

    @Override
    public void createOrder(Long userID, Long productID, Integer quantity) {
        Order order = new Order();
        order.setUserId(userID);
        order.setProductId(productID);
        ProductSeckill seckill = seckillMapper.getSeckillByProductID(productID);
        BigDecimal price;
        if(null == seckill){
            price = productMapper.getProductByID(productID).getPrice();
        }else{
            price = seckill.getPrice();
        }
        order.setPrice(price);
        order.setProductCount(quantity);
        order.setState(0);
        BigDecimal orderPrice = price.multiply(BigDecimal.valueOf(quantity));
        order.setOrderPrice(orderPrice);
        int ret = orderMapper.createOrder(order);
        if(ret <= 0){
            throw new GlobleException(ORDER_CREATE_FAIL);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        int ret = orderMapper.deleteOrder(id);
        if(ret <= 0){
            throw new GlobleException(ORDER_DELETE_FAIL);
        }
    }

    @Override
    public Order getOrderByID(Long id) {
        Order order = orderMapper.getOrderByID(id);
        if(null == order){
            throw new GlobleException(ORDER_NOT_EXIST);
        }
        return order;
    }

    @Override
    public List<Order> getOrdersByUserID(Long userID) {
        List<Order> orders = orderMapper.getOrdersByUserID(userID);
        return orders;
    }
}

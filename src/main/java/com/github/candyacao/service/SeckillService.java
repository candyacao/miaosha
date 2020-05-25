package com.github.candyacao.service;

import com.github.candyacao.model.ProductSeckill;

import java.util.List;

public interface SeckillService {
    ProductSeckill getSeckillByID(Long id);
    List<ProductSeckill> getSeckills();
    String addSeckill(String productID, ProductSeckill seckill);
    String updateSeckill(ProductSeckill seckill);
    String deleteSeckill(ProductSeckill seckill);
}

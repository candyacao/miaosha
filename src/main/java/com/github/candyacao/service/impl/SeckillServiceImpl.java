package com.github.candyacao.service.impl;

import com.github.candyacao.exception.GlobleException;
import com.github.candyacao.mapper.SeckillMapper;
import com.github.candyacao.model.ProductSeckill;
import com.github.candyacao.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.candyacao.common.enums.ResultStatus.PRODUCT_ISNOT_EXSIT;

@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    private SeckillMapper seckillMapper;
    @Override
    public ProductSeckill getSeckillByID(Long id) {
        ProductSeckill seckill = seckillMapper.getSeckillByID(id);
        if(null == seckill){
            throw new GlobleException(PRODUCT_ISNOT_EXSIT);
        }
        return seckill;
    }

    @Override
    public List<ProductSeckill> getSeckills() {
        List<ProductSeckill> seckills = seckillMapper.getSeckills();
        return seckills;
    }

    @Override
    public String addSeckill(String productID, ProductSeckill seckill) {
        seckillMapper.createSeckill(productID, seckill);
        return seckill.getId()+"";
    }

    @Override
    public String updateSeckill(ProductSeckill seckill) {
        seckillMapper.updateSeckill(seckill);
        return "更改秒杀商品库存成功";
    }

    @Override
    public String deleteSeckill(ProductSeckill seckill) {
        seckillMapper.deleteSeckill(seckill);
        return "删除秒杀商品信息成功";
    }
}

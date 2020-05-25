package com.github.candyacao.web;

import com.github.candyacao.common.resultbean.Result;
import com.github.candyacao.model.ProductSeckill;
import com.github.candyacao.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class SeckillController {
    @Autowired
    private SeckillService seckillService;

    @GetMapping("/getSeckillByID")
    public Result<ProductSeckill> getSeckillByID(Long id){
        Result<ProductSeckill> result = Result.build();
        ProductSeckill seckill = seckillService.getSeckillByID(id);
        result.setData(seckill);
        return result;
    }
    @GetMapping("/getSeckills")
    public Result<List<ProductSeckill>> getSeckill(){
        Result<List<ProductSeckill>> result = Result.build();
        List<ProductSeckill> seckills = seckillService.getSeckills();
        result.setData(seckills);
        return result;
    }
    @PostMapping("/addSeckills")
    public Result<String> addSeckill(String productID, ProductSeckill seckill){
        Result<String> result = Result.build();
        String seckillID = seckillService.addSeckill(productID, seckill);
        result.setData(seckillID);
        return result;
    }
    @PutMapping("/updateSeckill")
    public Result<String> updateSeckill(ProductSeckill seckill){
        Result<String> result = Result.build();
        String msg = seckillService.updateSeckill(seckill);
        result.setData(msg);
        return result;
    }
    @DeleteMapping("/deleteSeckill")
    public Result<String> deleteSeckill(ProductSeckill seckill){
        Result<String> result = Result.build();
        String msg = seckillService.deleteSeckill(seckill);
        result.setData(msg);
        return result;
    }
}

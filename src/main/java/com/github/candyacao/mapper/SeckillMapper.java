package com.github.candyacao.mapper;

import com.github.candyacao.model.ProductSeckill;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SeckillMapper {
    @Insert("INSERT INTO product_seckill(product_id,price,stock_count,start_date,end_date) VALUES(#{productID},#{seckill.price},#{seckill.stockCount},#{seckill.startDate},#{seckill.endDate})")
    void createSeckill(String productID, ProductSeckill seckill);
    @Select("SELECT * FROM product_seckill WHERE ID =#{id}")
    ProductSeckill getSeckillByID(Long id);
    @Select("SELECT * FROM product_seckill")
    List<ProductSeckill> getSeckills();
    @Update("UPDATE product_seckill set stock_count = stock_count - 1 where id =#{id} and stock_count>0")
    void updateSeckill(ProductSeckill seckill);
    @Delete("DELETE FROM product_seckill where id =#{id}")
    void deleteSeckill(ProductSeckill seckill);
}

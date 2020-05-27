package com.github.candyacao.mapper;

import com.github.candyacao.model.ProductSeckill;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SeckillMapper {
    @Insert("INSERT INTO product_seckill(product_id,price,stock_count,start_date,end_date) VALUES(#{productID},#{seckill.price},#{seckill.stockCount},#{seckill.startDate},#{seckill.endDate})")
    void createSeckill(String productID, ProductSeckill seckill);
    @Select("SELECT ps.id as seckillID,ps.price as seckillPrice,ps.stock_count as stockCount,ps.start_date as startDate,ps.end_date as endDate,p.name,p.title,p.img,p.detail FROM product p,product_seckill ps WHERE p.id = ps.product_id AND ps.id =#{id} ORDER BY ps.id")
    @Results(id = "seckillResults", value = {
            @Result(property = "id",column = "seckillID"),
            @Result(property = "price", column = "seckillPrice"),
            @Result(property = "stockCount", column = "stockCount"),
            @Result(property = "startDate", column = "startDate"),
            @Result(property = "endDate", column = "endDate"),
            @Result(property = "productInfo", column = "ps.product_id", one = @One(select = "com.github.candyacao.mapper.ProductMapper.getProductByID"))
    })
    ProductSeckill getSeckillByID(Long id);

    // 根据商品ID查询秒杀产品
    @Select("SELECT * FROM product_seckill WHERE product_id =#{id}")
    ProductSeckill getSeckillByProductID(Long id);

    @Select("SELECT ps.id as seckillID,ps.price as seckillPrice,ps.stock_count as stockCount,ps.start_date as startDate,ps.end_date as endDate,p.name,p.title,p.img,p.detail FROM product_seckill ps,product p WHERE ps.product_id = p.id ORDER BY ps.id")
    @ResultMap("seckillResults")
    List<ProductSeckill> getSeckills();

    @Update("UPDATE product_seckill set stock_count = stock_count - 1 where id =#{id} and stock_count>0")
    void updateSeckill(ProductSeckill seckill);

    @Delete("DELETE FROM product_seckill where id =#{id}")
    void deleteSeckill(ProductSeckill seckill);
}

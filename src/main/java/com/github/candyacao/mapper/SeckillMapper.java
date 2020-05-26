package com.github.candyacao.mapper;

import com.github.candyacao.model.ProductSeckill;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SeckillMapper {
    @Insert("INSERT INTO product_seckill(product_id,price,stock_count,start_date,end_date) VALUES(#{productID},#{seckill.price},#{seckill.stockCount},#{seckill.startDate},#{seckill.endDate})")
    void createSeckill(String productID, ProductSeckill seckill);
    // todo: 出来的信息不全
    //@Select("SELECT ps.id,p.name,p.title,p.img,p.detail,ps.price,ps.stock_count,ps.start_date,ps.end_date FROM product p,product_seckill ps WHERE p.id = ps.product_id AND ps.id =#{id}")
    /*@Results(id = "seckillResults", value = {
            @Result(property = "id",column = "ps.id"),
            @Result(property = "name", column = "p.name"),
            @Result(property = "title", column = "p.title"),
            @Result(property = "img", column = "p.img"),
            @Result(property = "detail", column = "p.detail"),
            @Result(property = "price", column = "ps.price"),
            @Result(property = "stockCount", column = "ps.stock_count"),
            @Result(property = "startDate", column = "ps.start_date"),
            @Result(property = "endDate", column = "ps.end_date")
    })*/
    @Select("SELECT * FROM product_seckill WHERE id =#{id}")
    ProductSeckill getSeckillByID(Long id);

    @Select("SELECT * FROM product_seckill")
   // @ResultMap("seckillResults")
    List<ProductSeckill> getSeckills();
    @Update("UPDATE product_seckill set stock_count = stock_count - 1 where id =#{id} and stock_count>0")
    void updateSeckill(ProductSeckill seckill);
    @Delete("DELETE FROM product_seckill where id =#{id}")
    void deleteSeckill(ProductSeckill seckill);
}

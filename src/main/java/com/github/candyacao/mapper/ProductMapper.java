package com.github.candyacao.mapper;

import com.github.candyacao.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("INSERT INTO product(name,title,img,detail,price,stock) VALUES(#{name}, #{title}, #{img}, #{detail}, #{price}, #{stock})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    int add(Product product);

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product getProductByID(Long id);

    @Update("<script>" +
                "UPDATE product" +
                "<trim prefix=\"SET\" suffixOverrides=\",\">" +
                    "<if test=\"name != null\">name=#{name},</if>" +
                    "<if test=\"title != null\">title=#{title},</if>" +
                    "<if test=\"img != null\">img=#{img},</if>" +
                    "<if test=\"detail != null\">detail=#{detail},</if>" +
                    "<if test=\"price != null\">price=#{price},</if>" +
                    "<if test=\"stock != null\">stock=#{stock},</if>" +
                "</trim>" +
                " WHERE id =#{id}" +
            "</script>")
    int updateProduct(Product product);

    @Update("Update product set stock = stock - 1 where id =#{id} and stock > 0")
    int decProductCount(Product product);

    @Delete("DELETE FROM product WHERE id =#{id}")
    int deleteProduct(Long id);
    // Todo: 获取某一类型的商品列表
   /* @Select("<script>" +
                "SELECT * FROM product " +
                    "<where>" +
                        "<if test=\"name != null and name != ''\">AND name LIKE CONCAT('%',#{name},'%')</if>" +
                        "<if test=\"title != null and title != ''\">AND title LIKE CONCAT('%',#{title},'%')</if>" +
                        "<if test=\"img != null and img != ''\">AND img =#{img}</if>" +
                        "<if test=\"detail != null and detail != ''\">AND detail LIKE CONCAT('%',#{detail},'%')</if>" +
                        "<if test=\"price != null and price != ''\">AND price =#{price}</if>" +
                    "</where>" +
            "</script>")
    List<Product> getProducts(Product product);*/
   @Select("SELECT * FROM product ")
   List<Product> getProducts();
}

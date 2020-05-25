package com.github.candyacao.mapper;

import com.github.candyacao.enums.UserSexEnum;
import com.github.candyacao.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("INSERT INTO product(name,title,img,detail,price,stock) VALUES(#{name}, #{title}, #{img}, #{detail}, #{price}, #{stock})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void add(Product product);

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product getProductByID(Long id);
    // Todo: 根据输入的参数进行更新
    @Update("Update product set stock = stock - 1 where id=#{id} and stock > 0")
    void updateProduct(Product product);

    @Delete("DELETE FROM product WHERE id =#{id}")
    void deleteProduct(Long id);
    // Todo: 获取某一类型的商品列表
    @Select("SELECT * FROM product")
    List<Product> getProducts();
}

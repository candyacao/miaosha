package com.github.candyacao.mapper;

import com.github.candyacao.model.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {
    @Insert("INSERT INTO cart(user_id,product_id,price,quantity,create_date,update_date) VALUES(#{userID},#{productID},#{price},#{quantity},#{createDate},#{updateDate})")
    int addCart(Cart cart);

    @Delete("DELETE FROM cart WHERE id =#{id}")
    int deleteCart(Cart cart);

    @Select("SELECT * FROM cart WHERE user_id =#{userID} ORDER BY update_date DESC")
    @Results(id = "cartResults", value={
            @Result(property = "id", column = "id"),
            @Result(property = "userID", column = "user_id"),
            @Result(property = "productID", column = "product_id"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date"),
    })
    List<Cart> getCarts(Long userID);

    @Select("SELECT * FROM cart WHERE user_id =#{userID} AND product_id =#{productID}")
    @ResultMap("cartResults")
    Cart getCartByUserAndGood(Long userID, Long productID);

    @Update("UPDATE cart SET quantity = quantity + 1,update_date=now() WHERE id =#{id}")
    int addProductCount(Long id);

    @Update("UPDATE cart SET quantity = quantity - 1,update_date=now() WHERE id =#{id} AND quantity > 0")
    int decProductCount(Long id);
}

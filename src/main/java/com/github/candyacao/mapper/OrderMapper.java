package com.github.candyacao.mapper;

import com.github.candyacao.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO " +
            "`order`(user_id,product_id,price,product_count,order_price,state,create_date) " +
            "VALUES(#{userId},#{productId},#{price},#{productCount},#{orderPrice},#{state},now())")
    int createOrder(Order order);

    @Delete("DELETE FROM `order` WHERE id =#{id}")
    int deleteOrder(Long id);

    @Select("SELECT * FROM `order` WHERE id =#{id}")
    @Results(id = "orderInfo", value = {
            @Result(property = "userId",column = "user_id"),
            @Result(property = "productId",column = "product_id"),
            @Result(property = "productCount",column = "product_count"),
            @Result(property = "orderPrice",column = "order_price"),
            @Result(property = "createDate",column = "create_date"),
            @Result(property = "endDate",column = "end_date")
    })
    Order getOrderByID(Long id);

    @Select("SELECT * FROM `order` WHERE user_id =#{userID}")
    @ResultMap("orderInfo")
    List<Order> getOrdersByUserID(Long userID);
}

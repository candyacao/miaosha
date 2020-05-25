package com.github.candyacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long id;  // 购物车ID
    private Long userID;   // 用户ID
    private Long productID;   // 商品ID
    private BigDecimal price;  // 商品价格
    private int quantity;   // 商品数量
    private Date createDate;  // 购物车创建时间
    private Date updateDate;  // 更新时间
}

package com.github.candyacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    // Todo: ID用uuid，查询如何处理
    private Long id;
    private String name;
    private String title;
    private String img;  // 商品图片
    private String detail; // 商品详细信息
    private BigDecimal price; // 商品价格
    private Integer stock; // 商品库存

}

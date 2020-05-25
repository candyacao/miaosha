package com.github.candyacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSeckill extends Product {
//    private Long id;
    private BigDecimal price;
    private Integer stockCount;
    private Date startDate;  // 秒杀日期开始日期
    private Date endDate;  // 秒杀结束日期Product
}

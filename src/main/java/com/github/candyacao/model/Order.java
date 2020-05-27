package com.github.candyacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Long productId;  // '商品ID'
    private BigDecimal price; // 商品价格
    private Integer productCount; // 商品数量
    private BigDecimal orderPrice; // 订单总金额
    private Integer state; // 订单状态 0:未支付｜1:微信支付｜2:支付宝支付｜3:银行卡支付
    private Date createDate; // 创建时间
    private Date endDate; // 结束时间
}

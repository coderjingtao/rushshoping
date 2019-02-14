package com.rushshopping.service.model;

import java.math.BigDecimal;

/**
 * Description: The domain model of customer order
 * Created by Jingtao Liu on 12/02/2019.
 */
public class OrderModel {
    private String id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private BigDecimal productPrice;//the product price at the time of purchase
    private BigDecimal orderPrice;
    private Integer promotionId;// if not null, make the order by promotion activity

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
}

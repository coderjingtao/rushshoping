package com.rushshopping.service.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: The domain model of product promotion
 * Created by Jingtao Liu on 13/02/2019.
 */
public class PromotionModel {
    private Integer id;

    private String promotionName;

    private Date startTime;

    private Date endTime;

    private Integer productId;

    private BigDecimal promotionPrice;

    private Integer status;//促销活动状态，1：not start 2: start 3: end

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

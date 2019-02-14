package com.rushshopping.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionDO {
    private Integer id;

    private String promotionName;

    private Date startTime;

    private Date endTime;

    private Integer productId;

    private BigDecimal promotionPrice;

    public PromotionDO(Integer id, String promotionName, Date startTime, Date endTime, Integer productId, BigDecimal promotionPrice) {
        this.id = id;
        this.promotionName = promotionName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.productId = productId;
        this.promotionPrice = promotionPrice;
    }

    public PromotionDO() {
        super();
    }

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
        this.promotionName = promotionName == null ? null : promotionName.trim();
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
}
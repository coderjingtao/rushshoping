package com.rushshopping.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Description: The domain model of product item
 * Created by Jingtao Liu on 12/02/2019.
 */
public class ProductModel {
    private Integer id;

    @NotBlank(message = "Product name cannot be empty.")
    private String name;

    @NotNull(message = "Product price is not filled.")
    @Min(value = 0, message = "Product price be greater than 0.")
    private BigDecimal price;

    @NotNull(message = "Product stock is not filled.")
    @Min(value = 0, message = "Product stock be greater than 0.")
    private Integer stock;

    @NotBlank(message = "Product description cannot be empty.")
    private String description;

    private Integer sales;//销量

    @NotBlank(message = "Product Picture cannot be empty.")
    private String imgUrl;//商品图片

    //It is integrated for product promotion
    // if promotionModel != null , it represents there's still an ongoing promotion activity.
    private PromotionModel promotionModel;

    public PromotionModel getPromotionModel() {
        return promotionModel;
    }

    public void setPromotionModel(PromotionModel promotionModel) {
        this.promotionModel = promotionModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

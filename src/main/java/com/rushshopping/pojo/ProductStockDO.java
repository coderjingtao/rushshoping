package com.rushshopping.pojo;

public class ProductStockDO {
    private Integer id;

    private Integer stock;

    private Integer productId;

    public ProductStockDO(Integer id, Integer stock, Integer productId) {
        this.id = id;
        this.stock = stock;
        this.productId = productId;
    }

    public ProductStockDO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
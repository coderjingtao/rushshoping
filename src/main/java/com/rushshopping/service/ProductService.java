package com.rushshopping.service;

import com.rushshopping.error.BusinessException;
import com.rushshopping.service.model.ProductModel;

import java.util.List;

/**
 * Description: the interface of Product Business Logic Service
 * Created by Jingtao Liu on 12/02/2019.
 */
public interface ProductService {

    ProductModel createProduct(ProductModel productModel) throws BusinessException;

    List<ProductModel> listProduct();

    ProductModel getProductById(Integer productId);

    boolean reduceStock(Integer productId, Integer quantity);

    boolean addSales(Integer productId,Integer quantity);
}

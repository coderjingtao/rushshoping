package com.rushshopping.service.impl;

import com.rushshopping.common.Constant;
import com.rushshopping.dao.ProductDOMapper;
import com.rushshopping.dao.ProductStockDOMapper;
import com.rushshopping.error.BusinessException;
import com.rushshopping.error.EmBusinessError;
import com.rushshopping.pojo.ProductDO;
import com.rushshopping.pojo.ProductStockDO;
import com.rushshopping.service.ProductService;
import com.rushshopping.service.PromotionService;
import com.rushshopping.service.model.ProductModel;
import com.rushshopping.service.model.PromotionModel;
import com.rushshopping.validator.ValidationResult;
import com.rushshopping.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: The implementation of product service
 * Created by Jingtao Liu on 12/02/2019.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ValidatorImpl validator;
    @Autowired
    ProductDOMapper productDOMapper;
    @Autowired
    ProductStockDOMapper productStockDOMapper;
    @Autowired
    PromotionService promotionService;

    @Override
    @Transactional
    public ProductModel createProduct(ProductModel productModel) throws BusinessException {
        //1. validation
        ValidationResult result = validator.validate(productModel);
        if(result.isHasError()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }
        //2. model --> DO (data object)
        ProductDO productDO = convertDOFromModel(productModel);
        //3. insert into database
        productDOMapper.insertSelective(productDO);
        productModel.setId(productDO.getId());
        ProductStockDO productStockDO = convertStockDOFromModel(productModel);
        productStockDOMapper.insertSelective(productStockDO);
        //4. return model filled with some fields from database
        return this.getProductById(productModel.getId());
    }

    @Override
    public List<ProductModel> listProduct() {

        List<ProductDO> productDOList = productDOMapper.selectAllProducts();
        //使用JDK1.8的stream api将productDOList中的data object 转化为 model
        List<ProductModel> productModelList = productDOList.stream().map(productDO -> {
            ProductStockDO productStockDO = productStockDOMapper.selectByProductId(productDO.getId());
            ProductModel productModel = convertModelFromDO(productDO,productStockDO);
            return productModel;
        }).collect(Collectors.toList());

        return productModelList;
    }

    @Override
    public ProductModel getProductById(Integer productId) {
        ProductDO productDO = productDOMapper.selectByPrimaryKey(productId);
        if(productDO == null)
            return null;
        ProductStockDO productStockDO = productStockDOMapper.selectByProductId(productDO.getId());
        ProductModel productModel = this.convertModelFromDO(productDO,productStockDO);
        //query whether product has promotion activity
        PromotionModel promotionModel = promotionService.getPromotionByProductId(productId);
        if(promotionModel != null && promotionModel.getStatus() != Constant.PromotionStatus.HAS_ENDED.getCode()){ // 3==end
            productModel.setPromotionModel(promotionModel);
        }
        return productModel;
    }

    @Override
    @Transactional
    public boolean reduceStock(Integer productId, Integer quantity) {
        int affectedRow = productStockDOMapper.reduceStock(productId, quantity);
        return affectedRow > 0;
    }

    @Override
    @Transactional
    public boolean addSales(Integer productId, Integer quantity) {
        int affectedRow = productDOMapper.updateSales(productId,quantity);
        return affectedRow > 0;
    }


    private ProductDO convertDOFromModel(ProductModel productModel){
        if(productModel == null)
            return null;
        ProductDO productDO = new ProductDO();
        BeanUtils.copyProperties(productModel,productDO);
        return productDO;
    }

    private ProductStockDO convertStockDOFromModel(ProductModel productModel){
        if(productModel == null)
            return null;
        ProductStockDO productStockDO = new ProductStockDO();
        productStockDO.setProductId(productModel.getId());
        productStockDO.setStock(productModel.getStock());
        return productStockDO;
    }

    private ProductModel convertModelFromDO(ProductDO productDO, ProductStockDO productStockDO){
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productDO,productModel);
        productModel.setStock(productStockDO.getStock());
        return productModel;
    }
}

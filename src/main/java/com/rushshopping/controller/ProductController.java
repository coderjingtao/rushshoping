package com.rushshopping.controller;

import com.rushshopping.common.Constant;
import com.rushshopping.common.ServerResponse;
import com.rushshopping.error.BusinessException;
import com.rushshopping.service.ProductService;
import com.rushshopping.service.model.ProductModel;
import com.rushshopping.service.model.PromotionModel;
import com.rushshopping.vo.ProductVO;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: The controller of Product
 * Created by Jingtao Liu on 12/02/2019.
 */
@Controller
@RequestMapping("/product")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ProductController extends BaseController{

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public ServerResponse createProduct(@RequestParam("name") String name,
                                        @RequestParam("price") BigDecimal price,
                                        @RequestParam("stock") Integer stock,
                                        @RequestParam("imgUrl") String imgUrl,
                                        @RequestParam("description") String description) throws BusinessException {
        ProductModel productModel = new ProductModel();
        productModel.setName(name);
        productModel.setPrice(price);
        productModel.setStock(stock);
        productModel.setImgUrl(imgUrl);
        productModel.setDescription(description);
        ProductModel returnedProductModel = productService.createProduct(productModel);
        ProductVO productVO = convertVOFromModel(returnedProductModel);
        return ServerResponse.create(productVO);
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.GET})
    @ResponseBody
    public ServerResponse detailProduct(@RequestParam("productId") Integer productId){
        ProductModel productModel = productService.getProductById(productId);
        ProductVO productVO = convertVOFromModel(productModel);
        return ServerResponse.create(productVO);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ServerResponse listProduct(){
        List<ProductModel> productModelList = productService.listProduct();
        List<ProductVO> productVOList = productModelList.stream().map(productModel -> {
            return convertVOFromModel(productModel);
        }).collect(Collectors.toList());
        return ServerResponse.create(productVOList);
    }

    private ProductVO convertVOFromModel(ProductModel productModel){
        if(productModel == null)
            return null;
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(productModel,productVO);
        //for promotion
        PromotionModel promotionModel = productModel.getPromotionModel();
        if(promotionModel != null){ //there's a promotion about this product
            productVO.setPromotionStatus(promotionModel.getStatus());
            productVO.setPromotionId(promotionModel.getId());
            productVO.setPromotionPrice(promotionModel.getPromotionPrice());
            DateTime startTime = new DateTime(promotionModel.getStartTime());
            productVO.setStartTime(startTime.toString("yyyy-MM-dd HH:mm:ss"));
        }else{
            productVO.setPromotionStatus(Constant.PromotionStatus.NO_PROMOTION.getCode());
        }
        return productVO;
    }
}

package com.rushshopping.service.impl;

import com.rushshopping.common.Constant;
import com.rushshopping.dao.OrderDOMapper;
import com.rushshopping.error.BusinessException;
import com.rushshopping.error.EmBusinessError;
import com.rushshopping.pojo.OrderDO;
import com.rushshopping.service.*;
import com.rushshopping.service.model.OrderModel;
import com.rushshopping.service.model.ProductModel;
import com.rushshopping.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Description: The implementation of user service
 * Created by Jingtao Liu on 12/02/2019.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private SequenceService sequenceService;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer productId, Integer quantity, Integer promotionId) throws BusinessException {
        //1.validation status: product exists? user is legal? quantity is normal?
        //1.1 validate product:
        ProductModel productModel = productService.getProductById(productId);
        if(productModel == null)
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"Product Not Exist");
        //1.2 validate user:
        UserModel userModel = userService.getUserById(userId);
        if(userModel == null)
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"User Not Exist");
        //1.3 validate quantity
        if(quantity < 0 || quantity> 99)
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"Quantity is incorrect");
        //1.4 validate promotion
        if(promotionId != null){
            //a. parameter promotionId is the same with promotionId in product
            if(productId.intValue() != productModel.getPromotionModel().getId())
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"Promotion info is incorrect");
            //b. promotion activity is ongoing
            if(productModel.getPromotionModel().getStatus() != Constant.PromotionStatus.ON_GOING.getCode())
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"Promotion is not ongoing");
        }


        //2.reduce stock of this product
        boolean reduceSuccess = productService.reduceStock(productId, quantity);
        if(!reduceSuccess)
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        //3.create order
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setProductId(productId);
        orderModel.setQuantity(quantity);

        if(promotionId != null){
            orderModel.setPromotionId(promotionId);
            orderModel.setProductPrice(productModel.getPromotionModel().getPromotionPrice());
        }else{
            orderModel.setProductPrice(productModel.getPrice());
        }
        orderModel.setOrderPrice(orderModel.getProductPrice().multiply(new BigDecimal(quantity)));

        //3.1 generate order number i.e. order id
        orderModel.setId(sequenceService.generateOrderNo());
        OrderDO orderDO = convertDOFromModel(orderModel);
        orderDOMapper.insertSelective(orderDO);
        //4. add sales to product
        boolean addSuccess = productService.addSales(productId, quantity);
        if(!addSuccess)
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR,"Add sales to product failed.");
        //5.return order
        return orderModel;
    }

    private OrderDO convertDOFromModel(OrderModel orderModel){
        if(orderModel == null)
            return null;
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel,orderDO);
        return orderDO;
    }
}

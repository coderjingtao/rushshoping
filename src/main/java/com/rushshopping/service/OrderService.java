package com.rushshopping.service;

import com.rushshopping.error.BusinessException;
import com.rushshopping.service.model.OrderModel;

/**
 * Description: The interface of Order service
 * Created by Jingtao Liu on 12/02/2019.
 */
public interface OrderService {
    OrderModel createOrder(Integer userId, Integer productId, Integer quantity, Integer promotionId) throws BusinessException;
}

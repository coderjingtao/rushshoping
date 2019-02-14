package com.rushshopping.service;

import com.rushshopping.service.model.PromotionModel;

/**
 * Description: The interface of promotion service
 * Created by Jingtao Liu on 13/02/2019.
 */
public interface PromotionService {
    PromotionModel getPromotionByProductId(Integer productId);
}

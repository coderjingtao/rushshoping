package com.rushshopping.service.impl;

import com.rushshopping.common.Constant;
import com.rushshopping.dao.PromotionDOMapper;
import com.rushshopping.pojo.PromotionDO;
import com.rushshopping.service.PromotionService;
import com.rushshopping.service.model.PromotionModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: The implementation of promotion service
 * Created by Jingtao Liu on 13/02/2019.
 */
@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionDOMapper promotionDOMapper;

    //Get the incoming or ongoing promotion activities
    @Override
    public PromotionModel getPromotionByProductId(Integer productId) {
        PromotionDO promotionDO = promotionDOMapper.selectByProductId(productId);
        PromotionModel promotionModel = convertModelFromDO(promotionDO);
        if(promotionModel == null)
            return null;
        //ues joda to judge promotion time
        DateTime startTime = new DateTime(promotionModel.getStartTime());
        DateTime endTime = new DateTime(promotionModel.getEndTime());
        if(startTime.isAfterNow()){
            promotionModel.setStatus(Constant.PromotionStatus.NOT_START.getCode());//has not started
        }else if(endTime.isBeforeNow()){
            promotionModel.setStatus(Constant.PromotionStatus.HAS_ENDED.getCode());//ended
        }else{
            promotionModel.setStatus(Constant.PromotionStatus.ON_GOING.getCode());//ongoing
        }
        return promotionModel;
    }


    private PromotionModel convertModelFromDO(PromotionDO promotionDO){
        if(promotionDO == null)
            return null;
        PromotionModel promotionModel = new PromotionModel();
        BeanUtils.copyProperties(promotionDO, promotionModel);
        return promotionModel;
    }
}

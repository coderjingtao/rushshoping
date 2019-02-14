package com.rushshopping.dao;

import com.rushshopping.pojo.PromotionDO;
import org.apache.ibatis.annotations.Param;

public interface PromotionDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PromotionDO record);

    int insertSelective(PromotionDO record);

    PromotionDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PromotionDO record);

    int updateByPrimaryKey(PromotionDO record);

    PromotionDO selectByProductId(@Param("productId") Integer productId);
}
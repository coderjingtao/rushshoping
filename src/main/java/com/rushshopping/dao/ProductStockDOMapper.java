package com.rushshopping.dao;

import com.rushshopping.pojo.ProductStockDO;
import org.apache.ibatis.annotations.Param;

public interface ProductStockDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductStockDO record);

    int insertSelective(ProductStockDO record);

    ProductStockDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductStockDO record);

    int updateByPrimaryKey(ProductStockDO record);

    ProductStockDO selectByProductId(@Param("productId") Integer productId);

    int reduceStock(@Param("productId") Integer productId,@Param("quantity") Integer quantity);
}
package com.rushshopping.dao;

import com.rushshopping.pojo.ProductDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductDO record);

    int insertSelective(ProductDO record);

    ProductDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductDO record);

    int updateByPrimaryKey(ProductDO record);

    List<ProductDO> selectAllProducts();

    int updateSales(@Param("productId") Integer productId,@Param("quantity") Integer quantity);
}
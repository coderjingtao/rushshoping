package com.rushshopping.dao;

import com.rushshopping.pojo.SequenceDO;
import org.apache.ibatis.annotations.Param;

public interface SequenceDOMapper {
    int deleteByPrimaryKey(String name);

    int insert(SequenceDO record);

    int insertSelective(SequenceDO record);

    SequenceDO selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(SequenceDO record);

    int updateByPrimaryKey(SequenceDO record);

    SequenceDO getSequenceByName(@Param("sequenceName") String sequenceName);
}
package com.rushshopping.service.impl;

import com.rushshopping.dao.SequenceDOMapper;
import com.rushshopping.pojo.SequenceDO;
import com.rushshopping.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description: The implementation of Sequence Service
 * Created by Jingtao Liu on 13/02/2019.
 */
@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    //订单号生成规则：订单号共有16位 ： 8+6+2
    @Transactional(propagation = Propagation.REQUIRES_NEW)//即使外层的方法事务回滚，即提交订单失败，这个sequence也会在数据库中更新掉，保证sequence的唯一性
    public String generateOrderNo() {
        StringBuilder sb = new StringBuilder();
        //前8位为时间信息，年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","");
        sb.append(nowDate);
        //中间6位为自增序列
        //1.获取当前sequence

        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_seq");//给sequence_info表加锁
        int sequence;
        if(sequenceDO.getCurrentValue()+sequenceDO.getStep()>sequenceDO.getMaxValue()){
            sequence = sequenceDO.getInitValue();
            sequenceDO.setCurrentValue(sequenceDO.getInitValue()+sequenceDO.getStep());
        }else{
            sequence = sequenceDO.getCurrentValue();
            sequenceDO.setCurrentValue(sequenceDO.getCurrentValue()+sequenceDO.getStep());
        }
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO); //更新sequence_info表，并释放锁，这时sequence才是唯一值
        //2.拼接，凑足6位
        String sequenceStr = String.valueOf(sequence);
        for(int i=0; i< 6- sequenceStr.length();i++)
            sb.append("0");
        sb.append(sequenceStr);
        //最后2位为分库分表位：00-99，
        //一般是用户id%100，也就是说，根据该路由规则，某用户下的订单会一直分配到一个固定的数据库中
        sb.append("00");
        return sb.toString();
    }
}

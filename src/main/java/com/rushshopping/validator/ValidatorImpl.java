package com.rushshopping.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Description: The encapsulation of Hibernate Validator
 * Created by Jingtao Liu on 12/02/2019.
 */
@Component //让Spring类扫描的时候会扫描到它
public class ValidatorImpl implements InitializingBean{

    private Validator  validator;

    //对任何一个bean对象，实现校验方法并返回校验结果
    public ValidationResult validate(Object bean){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if(constraintViolationSet.size() > 0) {//校验发现异常
            result.setHasError(true);
            constraintViolationSet.forEach(constraintViolation->{ //JDK1.8 lambda
                String propertyName = constraintViolation.getPropertyPath().toString();//哪个字段出错
                String errMsg = constraintViolation.getMessage();//字段错误信息
                result.getErrorMsgMap().put(propertyName,errMsg);
            });
        }
        return result;
    }

    @Override //当这个类初始化完成后，Spring会自动回调并执行这个方法
    public void afterPropertiesSet() throws Exception {
        //将Hibernate validator通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

}

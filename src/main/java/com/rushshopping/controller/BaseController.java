package com.rushshopping.controller;

import com.rushshopping.common.ServerResponse;
import com.rushshopping.error.BusinessException;
import com.rushshopping.error.EmBusinessError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: define common functions of all controllers, such as handling exceptions
 * Created by Jingtao Liu on 11/02/2019.
 */
public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    //定义exceptionHandler解决未被controller层吸收的exception
    //因为controller层是web系统业务处理最后一道关口
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String,Object> err = new HashMap<>();
        if(ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            err.put("errCode",businessException.getErrCode());
            err.put("errMsg",businessException.getErrMsg());
        }else{
            err.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            err.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        return ServerResponse.create("fail",err);
    }
}

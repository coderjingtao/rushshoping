package com.rushshopping.error;

/**
 * Description: Decorator Pattern to implement business exception
 * Created by Jingtao Liu on 11/02/2019.
 */
public class BusinessException extends Exception implements CommonError{

    private CommonError commonError;

    //直接接收EmBusinessError的错误信息
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    //接收自定义的错误信息
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }


    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}

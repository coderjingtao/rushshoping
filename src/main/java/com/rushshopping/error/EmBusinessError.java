package com.rushshopping.error;

/**
 * Description: The enumerate class of global error code
 * Created by Jingtao Liu on 11/02/2019.
 */
public enum EmBusinessError implements CommonError {

    //Common Error Code with 10000 in front
    PARAMETER_VALIDATION_ERROR(10001,"Parameters are invalid"),
    UNKNOWN_ERROR(10002,"Unknown error"),

    //Error Codes with "20000" in front refer to User Info Error
    USER_NOT_EXIST(20001,"User Not Exist"),
    USER_LOGIN_FAIL(20002,"Telephone or Password is incorrect."),
    USER_NOT_LOGIN(20003,"User Not Login"),

    //Error Codes with "30000" in front refer to Order Info Error
    STOCK_NOT_ENOUGH(30001,"Stock Not Enough"),
    ;

    private int errCode;
    private String errMsg;
    EmBusinessError(int errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    //Customize the specific common error message, such as email format error.
    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}

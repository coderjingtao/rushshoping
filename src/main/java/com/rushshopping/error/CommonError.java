package com.rushshopping.error;

/**
 * Description: the interface of common error
 * Created by Jingtao Liu on 11/02/2019.
 */
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}

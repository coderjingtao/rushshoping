package com.rushshopping.common;

/**
 * Description: Constants
 * Created by Jingtao Liu on 14/02/2019.
 */
public class Constant {
    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String LOGIN_USER = "LOGIN_USER";

    public enum PromotionStatus{
        NO_PROMOTION(0,"No any promotion"),
        NOT_START(1,"Has Promotion, but not start yet"),
        ON_GOING(2,"Has Promotion, and it's ongoing"),
        HAS_ENDED(3,"Has Promotion, but it has ended");

        private int code;
        private String value;
        PromotionStatus(int code,String value){
            this.code = code;
            this.value = value;
        }
        public int getCode() {
            return this.code;
        }
        public String getValue() {
            return this.value;
        }
    }

}

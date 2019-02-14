package com.rushshopping.controller;

import com.rushshopping.common.Constant;
import com.rushshopping.common.ServerResponse;
import com.rushshopping.error.BusinessException;
import com.rushshopping.error.EmBusinessError;
import com.rushshopping.service.UserService;
import com.rushshopping.service.model.UserModel;
import com.rushshopping.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Description: The controller of user requests
 * Created by Jingtao Liu on 11/02/2019.
 */
@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
//允许Ajax的回调方法允许跨域请求,前端需配合xhrFields:{withCredentials:true}属性设置，进行session共享
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    //本质是一个proxy，它的内部有一个ThreadLocal方式的map,让用户在每一个线程中去处理自己的request,
    //并且它有自动清除的机制，所以这样注入进来是没有问题的
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public ServerResponse login(@RequestParam(name="telephone") String telephone,@RequestParam(name="password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password))
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        String encryptPassword = EncodeByMD5(password);
        UserModel userModel = userService.validateLogin(telephone,encryptPassword);
        httpServletRequest.getSession().setAttribute(Constant.IS_LOGIN,true);
        httpServletRequest.getSession().setAttribute(Constant.LOGIN_USER,userModel);
        return ServerResponse.create(null);
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public ServerResponse register(@RequestParam(name="telephone") String telephoneNo,
                                   @RequestParam(name="otpCode") String otpCode,
                                   @RequestParam(name="password") String password,
                                   @RequestParam(name="name") String name,
                                   @RequestParam(name="gender") Integer gender,
                                   @RequestParam(name="age") Integer age) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //1.verify the telephone and OTP code
        String otp = (String) httpServletRequest.getSession().getAttribute(telephoneNo);
        if(!StringUtils.equals(otp,otpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"OTP code is inconsistent.");
        }
        //2. User register
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(new Byte(String.valueOf(gender)));
        userModel.setTelephone(telephoneNo);
        userModel.setRegisterMode("byPhone");
        userModel.setEncryptPassword(EncodeByMD5(password));
        userService.register(userModel);
        return ServerResponse.create(null);
    }


    //Get OPT Verification Code as SMS to user's mobile phone
    //OTP: One Time Password
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public ServerResponse getOtp(@RequestParam(name="telephone") String telephoneNo){
        //step1. Randomly Generate OPT Verification Code
        Random random = new Random();
        int randomInt = random.nextInt(90000) + 10000;//[10000,99999]
        String otpCode = String.valueOf(randomInt);
        //step2. correlate this code with user's mobile phone number
            //分布式系统中，会使用redis构建映射{phoneNO:optCode},它具有天然的映射性和过期属性
            //本系统简单使用HttpSession去构建映射
        httpServletRequest.getSession().setAttribute(telephoneNo,otpCode);
        //step3. Send it as SMS
        System.out.println("telephoneNo="+telephoneNo+" & otpCode="+otpCode);
        return ServerResponse.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public ServerResponse getUser(@RequestParam(name="id") Integer userId) throws BusinessException {
        UserModel userModel = userService.getUserById(userId);
        if(userModel == null)
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        UserVO userVO = convertFromModel(userModel);
        return ServerResponse.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel){
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }

    private String EncodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        return base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
    }

}

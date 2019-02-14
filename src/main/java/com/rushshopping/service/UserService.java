package com.rushshopping.service;

import com.rushshopping.error.BusinessException;
import com.rushshopping.service.model.UserModel;

/**
 * Description: The interface of User service
 * Created by Jingtao Liu on 11/02/2019.
 */
public interface UserService {
    UserModel getUserById(Integer userId);
    void register(UserModel userModel) throws BusinessException;
    UserModel validateLogin(String telephone,String encryptPassword) throws BusinessException;
}

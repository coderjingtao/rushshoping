package com.rushshopping.service.impl;

import com.rushshopping.dao.UserDOMapper;
import com.rushshopping.dao.UserPasswordDOMapper;
import com.rushshopping.error.BusinessException;
import com.rushshopping.error.EmBusinessError;
import com.rushshopping.pojo.UserDO;
import com.rushshopping.pojo.UserPasswordDO;
import com.rushshopping.service.UserService;
import com.rushshopping.service.model.UserModel;
import com.rushshopping.validator.ValidationResult;
import com.rushshopping.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: The implementation of User service
 * Created by Jingtao Liu on 11/02/2019.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;
    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer userId) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(userId);
        if(userDO == null)
            return null;
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        return convertFromDataObject(userDO,userPasswordDO);
    }

    @Override
    @Transactional //ensure 2 insert operations in one transaction.
    public void register(UserModel userModel) throws BusinessException {
        if(userModel == null)
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);

//        if(StringUtils.isEmpty(userModel.getName())
//                || userModel.getGender() == null
//                || userModel.getAge() == null
//                || StringUtils.isEmpty(userModel.getTelephone()))
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);

        ValidationResult result = validator.validate(userModel);
        if(result.isHasError())
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());

        UserDO userDO = convertFromModel(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        }catch (DuplicateKeyException ex){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"Telephone NO. has existed.");
        }

        userModel.setId(userDO.getId());
        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
    }

    @Override
    public UserModel validateLogin(String telephone, String encryptPassword) throws BusinessException {
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if(userDO == null)
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO,userPasswordDO);
        if(!StringUtils.equals(encryptPassword,userModel.getEncryptPassword()))
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        return userModel;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if(userDO == null)
            return null;
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if(userPasswordDO != null)
            userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
        return userModel;
    }

    private UserDO convertFromModel(UserModel userModel){
        if(userModel == null)
            return null;
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }

    private UserPasswordDO convertPasswordFromModel(UserModel userModel){
        if(userModel == null)
            return null;
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncryptPassword(userModel.getEncryptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }
}

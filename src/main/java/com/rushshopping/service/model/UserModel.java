package com.rushshopping.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description: The User business Model used inside system 用户核心领域模型
 * Created by Jingtao Liu on 11/02/2019.
 */
public class UserModel {
    private Integer id;

    @NotBlank(message = "Username cannot be empty.")
    private String name;

    @NotNull(message = "Gender is not filled.")
    private Byte gender;

    @NotNull(message = "Age is not filled.")
    @Min(value = 0,message = "Age must be greater than 0.")
    @Max(value = 150,message = "Age must be less than 150.")
    private Integer age;

    @NotBlank(message = "Telephone cannot be empty.")
    private String telephone;

    private String registerMode;

    private String thirdPartyId;

    @NotBlank(message = "Password cannot be empty.")
    private String encryptPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }
}

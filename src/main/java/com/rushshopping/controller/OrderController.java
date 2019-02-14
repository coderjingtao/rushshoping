package com.rushshopping.controller;

import com.rushshopping.common.ServerResponse;
import com.rushshopping.error.BusinessException;
import com.rushshopping.error.EmBusinessError;
import com.rushshopping.service.OrderService;
import com.rushshopping.service.model.OrderModel;
import com.rushshopping.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: The controller of order request
 * Created by Jingtao Liu on 13/02/2019.
 */
@Controller
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class OrderController extends BaseController{
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public ServerResponse createOrder(@RequestParam("productId") Integer productId,
                                      @RequestParam("quantity") Integer quantity,
                                      @RequestParam(name = "promotionId",required = false) Integer promotionId) throws BusinessException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(isLogin == null || !isLogin )
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        OrderModel orderModel = orderService.createOrder(userModel.getId(),productId,quantity,promotionId);
        return ServerResponse.create(orderModel);
    }
}

package com.imooc.sell.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.imooc.sell.DTO.OrderDTO;
import com.imooc.sell.config.WechatAccountConfig;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.response.PayResponse;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/2/3</pre>
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl){
        ModelAndView mv = new ModelAndView("pay");
        // 查询订单
       OrderDTO orderDTO = orderService.findOne(orderId);
       if(orderDTO == null){
           log.info("【微信支付创建订单】失败，订单不存在");
           throw new SellException(ResultEnum.ORDER_NOT_EXIST);
       }

       JSONObject payResponse = payService.createByPayjs(orderDTO);
       Map map = new HashMap<>();
       map.put("payResponse",payResponse.get("jsapi"));
       map.put("returnUrl",returnUrl);
       log.info("【微信支付】返回对象 payResponse = {}",payResponse);

        return new  ModelAndView("pay/create",map);
    }


    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
       payService.notify(notifyData);

       return new ModelAndView("pay/success");
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}




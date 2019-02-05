package com.imooc.sell.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.imooc.sell.DTO.OrderDTO;
import com.imooc.sell.config.WechatAccountConfig;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.imooc.sell.utils.*;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/2/3</pre>
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    private static final String OPENID = "o7LFAwX27mxJnaGNqOI5KSzCSgYI";

    @Autowired
    private WechatAccountConfig accountConfig;

    @Autowired
    private OrderService orderService;

    @Override
    public JSONObject createByPayjs(OrderDTO orderDTO) {
        Map payMap = new HashMap<>();
        payMap.put("mchid",accountConfig.getMchId());
        payMap.put("total_fee",""+new Integer(1));
        payMap.put("out_trade_no",orderDTO.getOrderId());
        payMap.put("body",ORDER_NAME);
        payMap.put("attach","test");
        payMap.put("notify_url",accountConfig.getNotifyUrl());
        payMap.put("openid",OPENID);
        log.info("key===="+accountConfig.getMchKey());
        String md5 = SignUtil.sign(payMap,accountConfig.getMchKey());
        String sign = md5.toUpperCase();
        payMap.put("sign",sign);



        String reponse = HttpInvoker.readContentFromPost("https://payjs.cn/api/jsapi",payMap);
        JSONObject jsonObject = JSONObject.parseObject(reponse);
        log.info("【微信支付】接口返回 jsonObject={}",jsonObject);

        return jsonObject;
    }

    @Override
    public Map notify(String notifyData) {
        // 验证签名
        // 支付状态
        // 支付金额
        // 支付人
        log.info("【微信支付回调】notifyData={}",notifyData);
        Map payResponse = getRespnse(notifyData);
        log.info("【微信支付回调】 response={}",payResponse);

        OrderDTO orderDTO = orderService.findOne(payResponse.get("out_trade_no").toString());
        if(orderDTO == null){
            log.info("【微信支付回调】异常，订单不存在");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        Double orderAmount = orderDTO.getOrderAmount().multiply(new BigDecimal(10)).doubleValue();
        Double totalFee = new Double(payResponse.get("total_fee").toString());

        if(MathUtil.equals(orderAmount,totalFee)){
            log.info("【微信支付回调】金额异常，orderId={},微信金额={},系统金额={}",
                    payResponse.get("out_trade_no"),
                    payResponse.get("total_fee"),
                    orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WECHAT_PAY_TOTAL_ERROR);
        }

        orderService.paid(orderDTO);

        return payResponse;
    }


    @Override
    public Map refuse(OrderDTO orderDTO) {
        Map refuseMap = new HashMap();
        refuseMap.put("payjs_order_id",orderDTO.getOrderId());
        String md5 = SignUtil.sign(refuseMap,accountConfig.getMchKey());
        refuseMap.put("sign",md5.toUpperCase());

        String reponse = HttpInvoker.readContentFromPost("https://payjs.cn/api/refund",refuseMap);
        JSONObject jsonObject = JSONObject.parseObject(reponse);
        log.info("【微信退款】接口返回 jsonObject={}",jsonObject);

        return null;
    }

    private Map getRespnse(String notifyData) {
        Map result = new HashMap();
        String[] dataArr = notifyData.split("&");
        for(String data:dataArr){
           String key = data.substring(0,data.indexOf("="));
           String value = data.substring(data.indexOf("=")+1,data.length());
           result.put(key,value);
        }

        return result;
    }

    // private BestPayServiceImpl bestPayService;

   /* @Override
    public void create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】request = {}", JsonUtil.toJson(payRequest));


        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】response = {}",JsonUtil.toJson(payResponse));
    }*/
}




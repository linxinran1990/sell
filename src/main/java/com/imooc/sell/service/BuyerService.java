package com.imooc.sell.service;

import com.imooc.sell.DTO.OrderDTO;

public interface BuyerService {

    // 查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    // 取消一个订单
    OrderDTO cancelOrder(String openId,String orderId);
}

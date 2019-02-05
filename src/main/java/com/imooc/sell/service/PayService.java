package com.imooc.sell.service;

import com.alibaba.fastjson.JSONObject;
import com.imooc.sell.DTO.OrderDTO;

import java.util.Map;


public interface PayService {
    //void create(OrderDTO orderDTO);

    JSONObject createByPayjs(OrderDTO orderDTO);

    Map notify(String notifyData);

    Map refuse(OrderDTO orderDTO);
}

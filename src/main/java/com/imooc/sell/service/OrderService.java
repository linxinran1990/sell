package com.imooc.sell.service;

import com.imooc.sell.DTO.OrderDTO;
import com.imooc.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/26</pre>
 */
public interface OrderService {

    /** 订单创建.**/
    OrderDTO create(OrderDTO orderDTO);

    /** 查询单个订单.*/
    OrderDTO findOne(String orderId);

    /** 查询订单列表.*/
    Page<OrderDTO> findList(String buyerOpenid,PageRequest pageRequest);

    /** 取消订单.*/
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单.*/
    OrderDTO finsh(OrderDTO orderDTO);

    /** 支付订单 .*/
    OrderDTO paid(OrderDTO orderDTO);
}




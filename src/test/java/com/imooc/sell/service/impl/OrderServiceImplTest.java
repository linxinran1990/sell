package com.imooc.sell.service.impl;

import com.imooc.sell.DTO.OrderDTO;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private static final String ORDERID = "1548508557664479940";

    private static final String OPENID = "101010";

    @Test
    public void create() throws Exception {

        // 1.订单信息
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("锦绣温泉");
        orderDTO.setBuyerName("陈大爷2l");
        orderDTO.setBuyerOpenid("101010");
        orderDTO.setBuyerPhone("13900000000");
        orderDTO.setOrderId(ORDERID);

        // 2.订单详情信息
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(ORDERID);
        orderDetail.setDetailId(KeyUtil.genUniqueKey());
        orderDetail.setProductId("sss");
        orderDetail.setProductQuantity(100);
        orderDetail.setProductName("皮蛋瘦肉21");
        orderDetailList.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);

    }


    @Test
    public void findOne(){
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        log.info("订单信息："+orderDTO);
       Assert.assertEquals(ORDERID,orderDTO.getOrderId());
    }

    @Test
    public void findList(){
        PageRequest pageRequest = new PageRequest(0,1);
        Page<OrderDTO> orderDTOPage = orderService.findList(OPENID,pageRequest);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }


    @Test
    public void cancle(){
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finsh(){
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.finsh(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid(){
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }
}

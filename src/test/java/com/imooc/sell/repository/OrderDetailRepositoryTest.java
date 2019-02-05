package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;


    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456789");
        orderDetail.setOrderId("12345");
        orderDetail.setProductIcon("http://XXXX");
        orderDetail.setProductId("ssf3");
        orderDetail.setProductName("肉丸");
        orderDetail.setProductPrice(new BigDecimal(3.4));
        orderDetail.setProductQuantity(2);

        OrderDetail result = repository.save(orderDetail);
    }

    @Test
    public void findByOrderId(){
        List<OrderDetail> result= repository.findByOrderId("12345");

        Assert.assertNotEquals(0,result.size());
    }


}
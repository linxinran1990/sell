package com.imooc.sell.converter;

import com.imooc.sell.DTO.OrderDTO;
import com.imooc.sell.dataobject.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/26</pre>
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e ->
            converter(e)
        ).collect(Collectors.toList());
    }
}




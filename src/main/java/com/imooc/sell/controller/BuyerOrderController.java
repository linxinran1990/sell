package com.imooc.sell.controller;

import com.imooc.sell.DTO.OrderDTO;
import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/26</pre>
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
    *@param
    *@return
    *@exception
    * @author
    * @Time
    */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("[创建订单]参数异常，orderStatus={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单]购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createOrder = orderService.create(orderDTO);

        Map<String,String> result = new HashMap<>();
        result.put("orderId",createOrder.getOrderId());

        return ResultVOUtil.success(result);
    }


    // 列表
    @GetMapping("list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String opneid,
                                         @RequestParam("page") Integer page,
                                         @RequestParam("size") Integer size){
        if(StringUtils.isEmpty(opneid)){
            log.error("[查询订单] openid 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(opneid,request);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }


    // 详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String opneid,
                                         @RequestParam("orderId") String orderId){

        OrderDTO orderDTO = buyerService.findOrderOne(opneid,orderId);
        return ResultVOUtil.success(orderDTO);
    }

    // 详情
    @GetMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String opneid,
                                     @RequestParam("orderId") String orderId){

        buyerService.cancelOrder(opneid,orderId);
        return ResultVOUtil.success("");
    }
}




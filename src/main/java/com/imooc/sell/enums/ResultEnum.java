package com.imooc.sell.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/26</pre>
 */
@Getter
public enum  ResultEnum {

    PARAM_ERROR(1,"参数异常"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态错误"),

    ORDER_UPDATE_ERROR(15,"订单状态修改失败"),

    ORDER_DETAIL_EMPTY(16,"订单中无订单详情"),

    PAY_STATUS_ERROR(17,"订单支付状态异常"),

    CART_EMPTY(18,"购物车为空"),

    ORDER_OWNER_ERROE(19,"openid不一致"),

    WECHAT_MP_ERROR(20,"微信公众号方面错误"),

    WECHAT_PAY_TOTAL_ERROR(21,"微信支付金额异常"),
        ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}




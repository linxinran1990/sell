package com.imooc.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/26</pre>
 */
@Data
public class OrderForm {

    @NotEmpty(message = "名字必须填")
    private String name;

    @NotEmpty(message = "手机必须填")
    private String phone;

    @NotEmpty(message = "地址必须填")
    private String address;

    @NotEmpty(message = "openid必须填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}




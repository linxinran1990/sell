package com.imooc.sell.DTO;

import lombok.Data;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/2/3</pre>
 */
@Data
public class PayDTO {

    private String mchid;

    private String totalFee;

    private String outTradeNo;

    private String body;

    private String attach;

    private String notifyUrl;

    private String openid;

    private String sign;
}




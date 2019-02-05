package com.imooc.sell.response;

import lombok.Data;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/2/3</pre>
 */
@Data
public class PayResponse {

    private String returnCode;

    private String returnMsg;

    private String payjsOrderId;

    private String jsapi;

    private String sign;
}




package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/26</pre>
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }
}




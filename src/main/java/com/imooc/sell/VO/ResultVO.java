package com.imooc.sell.VO;

import lombok.Data;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/6</pre>
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}




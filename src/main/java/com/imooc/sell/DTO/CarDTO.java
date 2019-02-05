package com.imooc.sell.DTO;

import lombok.Data;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/26</pre>
 */
@Data
public class CarDTO {

    /** 商品id.**/
    private String productId;

    /** 数量. **/
    private Integer productQuantity;

    public CarDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}




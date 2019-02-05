package com.imooc.sell.service;

import com.imooc.sell.DTO.CarDTO;
import com.imooc.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/6</pre>
 */
public interface ProductService {

    /** 查询一个. **/
    ProductInfo findOne(String productId);

    List<ProductInfo> findAll();

    Page<ProductInfo> findAll(Pageable page);

    ProductInfo save(ProductInfo productInfo);

    // 加库存
    void increaseStock(List<CarDTO> carDTOList);


    // 减库存
    void decreaseStock(List<CarDTO> carDTOList);
}




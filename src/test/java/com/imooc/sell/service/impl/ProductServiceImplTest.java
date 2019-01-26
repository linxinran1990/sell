package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest extends ProductServiceImpl {

    @Autowired
    private ProductServiceImpl service;

    @Test
    public void findOne() throws Exception {
        ProductInfo info = service.findOne("sss");
        System.out.println(info);
    }

    @Test
    public void findAllSta() throws Exception {
        List<ProductInfo> list = service.findAll();
        System.out.println(list);
    }


    @Test
    public void findAll1() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> infosPage = service.findAll(request);
        System.out.println(infosPage);
     }

    @Test
    public void save() throws Exception {
    }

}
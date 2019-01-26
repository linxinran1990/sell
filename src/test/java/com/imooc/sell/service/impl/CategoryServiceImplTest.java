package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest extends CategoryServiceImpl {

    @Autowired
    private CategoryServiceImpl service;

    @Test
    public void findOne() throws Exception {
        service.findOne(1);
    }

    @Test
    public void findAllBy()  {
      List<ProductCategory> list= service.findAll();
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<Integer> categroyIds = new ArrayList<Integer>();
        categroyIds.add(1);
        categroyIds.add(2);
        List<ProductCategory> list= service.findByCategoryTypeIn(categroyIds);
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("linda",3);
        service.save(productCategory);
    }

}
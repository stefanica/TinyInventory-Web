package com.tinyinventory.app.controller;

import com.tinyinventory.app.model.Product;
import com.tinyinventory.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping ("/getProducts/{username}")
    public List<Product> getProducts(@PathVariable String username) {

        return productService.getAllUserProducts(username);
    }


}

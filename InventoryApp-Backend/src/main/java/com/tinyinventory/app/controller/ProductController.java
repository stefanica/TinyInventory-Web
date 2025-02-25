package com.tinyinventory.app.controller;

import com.tinyinventory.app.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {


    @GetMapping ("/getProduct")
    public Product getProduct() {

        return ;
    }


}

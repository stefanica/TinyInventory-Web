package com.tinyinventory.app.service;


import com.tinyinventory.app.model.Product;
import com.tinyinventory.app.model.Users;
import com.tinyinventory.app.repo.ProductRepo;
import com.tinyinventory.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllUserProducts(String username) {

        int userId = 0;

        Optional<Users> userOptional = userRepo.findUserByUsername(username);
        if (!userOptional.isPresent()) {
            return new ArrayList<Product>();
        } else {
            userId = userOptional.get().getId();
        }

        Optional<List<Product>> productList = productRepo.findAllByUserId(userId);
        return productList.orElseGet(ArrayList::new);

    }
}

package com.tinyinventory.app.service;


import com.tinyinventory.app.dto.ProductResponseDto;
import com.tinyinventory.app.dto.UserResponseDto;
import com.tinyinventory.app.model.Product;
import com.tinyinventory.app.model.User;
import com.tinyinventory.app.repo.ProductRepo;
import com.tinyinventory.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;

    public List<ProductResponseDto> getAllUserProducts(String username) {
        int userId = 0;

        //Get useId from database if we know the username
        Optional<User> userOptional = userRepo.findUserByUsername(username);
        if (userOptional.isEmpty()) {
            return new ArrayList<ProductResponseDto>();
        } else {
            userId = userOptional.get().getId();
        }

        //get the list of products. This also contains User, that contains password
        Optional<List<Product>> optionalProductList = productRepo.findAllByUserId(userId);
        List<Product> productList = optionalProductList.orElseGet(ArrayList::new);

        //create a new list where the Users are stripped of passwords
        List<ProductResponseDto> response = new ArrayList<>();
        for (Product product : productList) {
            response.add(new ProductResponseDto(product));
        }

        return response;
    }

    public void saveProduct(Product product, String username) {
        User user = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setPassword("redacted");

        product.setUserId(user.getId());
        product.setUser(user);

        try {
            productRepo.save(product);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save product");
        }
    }
}

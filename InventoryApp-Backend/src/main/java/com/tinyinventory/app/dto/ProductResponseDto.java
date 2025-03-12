package com.tinyinventory.app.dto;

import com.tinyinventory.app.model.Product;

public class ProductResponseDto {
    private Long id;
    private UserResponseDto userResponseDto;
    private int userId;
    private String name;
    private int code;
    private int quantity;
    private int price;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.userResponseDto = new UserResponseDto(product.getUser());
        this.userId = product.getUserId();
        this.name = product.getName();
        this.code = product.getCode();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }


}

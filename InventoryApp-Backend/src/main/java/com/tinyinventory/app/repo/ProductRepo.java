package com.tinyinventory.app.repo;

import com.tinyinventory.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {


    //SQL: SELECT * FROM products WHERE user_id = ?1
    //JPA SQL: @Query("SELECT p FROM products p WHERE p.user_id = :userId")
    Optional<List<Product>> findAllByUserId(int userId);

}

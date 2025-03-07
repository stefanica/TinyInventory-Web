package com.tinyinventory.app.repo;

import com.tinyinventory.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {


    //SQL: SELECT * FROM PRODUCT WHERE userId = ?1
    //JPA SQL: @Query(SELECT p FROM Product p WHERE p.userID = :userId)
    Optional<List<Product>> findAllByUserId(int userId);

}

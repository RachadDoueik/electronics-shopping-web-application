package com.shoppingApp.shoppingApp.repository;

import com.shoppingApp.shoppingApp.model.Category;
import com.shoppingApp.shoppingApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String productName);

    List<Product> findByCategoryName(String categoryName);

    List<Product> findByBrand(String brandName);

    List<Product> category(Category category);

    List<Product> findByCategoryNameAndBrand(String categoryName, String brandName);

    List<Product> findByBrandAndName(String brandName, String productName);

    Long countProductByBrandAndName(String brandName, String productName);
}
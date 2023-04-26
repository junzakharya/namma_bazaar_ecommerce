package com.example.namma_bazaar_ecommerce.repository;

import com.example.namma_bazaar_ecommerce.Enum.ProductCategory;
import com.example.namma_bazaar_ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductCategory(ProductCategory productCategory);
}

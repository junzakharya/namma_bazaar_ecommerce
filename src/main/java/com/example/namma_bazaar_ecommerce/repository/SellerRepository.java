package com.example.namma_bazaar_ecommerce.repository;

import com.example.namma_bazaar_ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findByEmailId(String email);

    @Query(value = "select * from seller", nativeQuery = true)
    List<Seller> getAllSeller();

    void deleteByEmailId(String emailId);
}

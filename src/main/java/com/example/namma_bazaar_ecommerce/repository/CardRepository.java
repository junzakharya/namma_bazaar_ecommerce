package com.example.namma_bazaar_ecommerce.repository;

import com.example.namma_bazaar_ecommerce.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}

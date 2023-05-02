package com.example.namma_bazaar_ecommerce.repository;

import com.example.namma_bazaar_ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Customer findByMobNo(String mobNo);

    @Query(value = "select * from customer c where c.age =:age", nativeQuery = true)
    List<Customer> getCustomerByAge(int age);

    @Query(value = "SELECT c.* FROM Customer c WHERE c.cards.cardType =:cardType", nativeQuery = true)
    List<Customer> getCustomerByCard(String cardType);

    void deleteByEmailId(String emailId);
}

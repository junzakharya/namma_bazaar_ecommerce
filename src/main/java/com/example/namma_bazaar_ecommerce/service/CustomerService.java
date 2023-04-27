package com.example.namma_bazaar_ecommerce.service;

import com.example.namma_bazaar_ecommerce.dto.requestDto.CustomerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CustomerResponseDto;
import com.example.namma_bazaar_ecommerce.exception.MobNoAlreadyExistException;
import com.example.namma_bazaar_ecommerce.model.Cart;
import com.example.namma_bazaar_ecommerce.model.Customer;
import com.example.namma_bazaar_ecommerce.repository.CustomerRepository;
import com.example.namma_bazaar_ecommerce.transformers.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobNoAlreadyExistException {
        if(customerRepository.findByMobNo(customerRequestDto.getMobNo())!=null){
            throw new MobNoAlreadyExistException("User Already Exists");
        }
        Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
        Cart cart= Cart
                .builder().numberOfItems(0)
                .customer(customer)
                .cartTotal(0)
                .build();
        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
    }
}

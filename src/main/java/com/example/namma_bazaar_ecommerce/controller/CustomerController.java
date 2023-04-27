package com.example.namma_bazaar_ecommerce.controller;

import com.example.namma_bazaar_ecommerce.dto.requestDto.CustomerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CustomerResponseDto;
import com.example.namma_bazaar_ecommerce.exception.MobNoAlreadyExistException;
import com.example.namma_bazaar_ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add") // to add a customer
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobNoAlreadyExistException {
       return  customerService.addCustomer(customerRequestDto);
    }
}

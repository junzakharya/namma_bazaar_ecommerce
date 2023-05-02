package com.example.namma_bazaar_ecommerce.controller;

import com.example.namma_bazaar_ecommerce.Enum.CardType;
import com.example.namma_bazaar_ecommerce.dto.requestDto.CustomerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CustomerResponseDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.SellerResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidCustomerException;
import com.example.namma_bazaar_ecommerce.exception.InvalidSellerException;
import com.example.namma_bazaar_ecommerce.exception.MobNoAlreadyExistException;
import com.example.namma_bazaar_ecommerce.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;

    @PostMapping("/add") // to add a customer
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobNoAlreadyExistException {
       return  customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/getByPhone") //to find a customer
    public CustomerResponseDto getCustomerByEmail(@RequestParam String mobNo) throws InvalidCustomerException {
       return customerService.getCustomerByMobNo(mobNo);
    }

    @GetMapping("/getByAge") // gives all customers of a particular age
    public List<CustomerResponseDto> getCustomerByAge(@RequestParam int age){
        return customerService.getCustomerByAge(age);
    }

    @GetMapping("/GetFromCardType") // gives the customers who use particular card
    public List<CustomerResponseDto> getCustomerByCard(@RequestParam String cardType){
        return customerService.getCustomerByCard(cardType);
    }

    @DeleteMapping("/delete") //deletes customer from db
    public String deleteCustomer(@RequestParam String emailId) throws InvalidCustomerException {
        return customerService.deleteCustomer(emailId);
    }

    @PutMapping("/updateInfo") // to update info
    public CustomerResponseDto updateInfo(@RequestParam String mobNo,@RequestBody CustomerRequestDto customerRequestDto ) throws InvalidCustomerException {
        return customerService.updateCustomerInfo(mobNo, customerRequestDto);
    }

}

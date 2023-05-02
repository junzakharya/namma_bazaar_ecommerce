package com.example.namma_bazaar_ecommerce.service;

import com.example.namma_bazaar_ecommerce.Enum.CardType;
import com.example.namma_bazaar_ecommerce.dto.requestDto.CustomerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.requestDto.SellerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CustomerResponseDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.SellerResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidCustomerException;
import com.example.namma_bazaar_ecommerce.exception.InvalidSellerException;
import com.example.namma_bazaar_ecommerce.exception.MobNoAlreadyExistException;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobNoAlreadyExistException;

    public CustomerResponseDto getCustomerByMobNo(String emailId) throws InvalidCustomerException;

    public List<CustomerResponseDto> getCustomerByAge(int age);

    public List<CustomerResponseDto> getCustomerByCard(String cardType);

    public String deleteCustomer(String emailId) throws InvalidCustomerException;

    public CustomerResponseDto updateCustomerInfo(String mobNo, CustomerRequestDto customerRequestDto) throws InvalidCustomerException;

}

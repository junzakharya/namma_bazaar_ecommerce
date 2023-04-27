package com.example.namma_bazaar_ecommerce.transformers;

import com.example.namma_bazaar_ecommerce.dto.requestDto.CustomerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CustomerResponseDto;
import com.example.namma_bazaar_ecommerce.model.Customer;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

public class CustomerTransformer {
    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer
                .builder()
                .mobNo(customerRequestDto.getMobNo())
                .emailId(customerRequestDto.getEmailId())
                .address(customerRequestDto.getAddress())
                .age(customerRequestDto.getAge())
                .name(customerRequestDto.getName())
                .build();

    }
    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto
                .builder().message("Hi "+ customer.getName()+ " Welcome to Namma Bazar" )
                .build();
    }
}

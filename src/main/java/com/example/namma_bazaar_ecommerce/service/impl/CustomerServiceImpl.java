package com.example.namma_bazaar_ecommerce.service.impl;

import com.example.namma_bazaar_ecommerce.Enum.CardType;
import com.example.namma_bazaar_ecommerce.dto.requestDto.CustomerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CustomerResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidCustomerException;
import com.example.namma_bazaar_ecommerce.exception.InvalidSellerException;
import com.example.namma_bazaar_ecommerce.exception.MobNoAlreadyExistException;
import com.example.namma_bazaar_ecommerce.model.Cart;
import com.example.namma_bazaar_ecommerce.model.Customer;
import com.example.namma_bazaar_ecommerce.model.Seller;
import com.example.namma_bazaar_ecommerce.repository.CustomerRepository;
import com.example.namma_bazaar_ecommerce.service.CustomerService;
import com.example.namma_bazaar_ecommerce.transformers.CustomerTransformer;
import com.example.namma_bazaar_ecommerce.transformers.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
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

    @Override
    public CustomerResponseDto getCustomerByMobNo(String mobileNo) throws InvalidCustomerException {
        Customer customer;
        try{
            customer = customerRepository.findByMobNo(mobileNo);
        }
        catch (Exception e){
            throw new InvalidCustomerException("invalid mobile number");
        }

        return CustomerTransformer.customerToCustomerResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDto> getCustomerByAge(int age) {
       List<Customer> customers = customerRepository.getCustomerByAge(age);

       List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();

       for(Customer customer:customers){
           customerResponseDtos.add(CustomerTransformer.customerToCustomerResponseDto(customer));
       }
       return customerResponseDtos;
    }

    @Override
    public List<CustomerResponseDto> getCustomerByCard(String cardType) {
        List<Customer> customers = customerRepository.getCustomerByCard(cardType);

        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();

        for(Customer customer:customers){
            customerResponseDtos.add(CustomerTransformer.customerToCustomerResponseDto(customer));
        }
        return customerResponseDtos;
    }

    @Override
    public String deleteCustomer(String emailId) throws InvalidCustomerException {
        customerRepository.deleteByEmailId(emailId);

        return "deleted successfully";
    }

    @Override
    public CustomerResponseDto updateCustomerInfo(String mobNo, CustomerRequestDto customerRequestDto) throws InvalidCustomerException {
        Customer customer;
        try{
            customer = customerRepository.findByMobNo(mobNo);
        }
        catch (Exception e){
            throw new InvalidCustomerException("invalid mobNo");
        }
        customer.setName(customerRequestDto.getName());
        customer.setAge(customerRequestDto.getAge());
        customer.setEmailId(customerRequestDto.getEmailId());
        customer.setMobNo(customerRequestDto.getMobNo());

        customerRepository.save(customer);

        return CustomerTransformer.customerToCustomerResponseDto(customer);
    }
}

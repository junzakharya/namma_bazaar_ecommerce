package com.example.namma_bazaar_ecommerce.service;

import com.example.namma_bazaar_ecommerce.dto.requestDto.OrderRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.OrderResponseDto;
import com.example.namma_bazaar_ecommerce.model.Card;
import com.example.namma_bazaar_ecommerce.model.Customer;
import com.example.namma_bazaar_ecommerce.model.Ordered;

public interface OrderService {
    public Ordered placeOrder(Customer customer, Card card) throws Exception;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;

    public String generateMaskedCard(String cardNo);
}

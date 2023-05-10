package com.example.namma_bazaar_ecommerce.service;

import com.example.namma_bazaar_ecommerce.dto.requestDto.CheckoutCartRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CartResponseDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.OrderResponseDto;
import com.example.namma_bazaar_ecommerce.model.Item;

public interface CartService {

    public CartResponseDto saveCart(Integer customerId, Item item);

    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception;

    }

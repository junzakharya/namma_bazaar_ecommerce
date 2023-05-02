package com.example.namma_bazaar_ecommerce.service;

import com.example.namma_bazaar_ecommerce.dto.requestDto.CardRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CardResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidCustomerException;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
}

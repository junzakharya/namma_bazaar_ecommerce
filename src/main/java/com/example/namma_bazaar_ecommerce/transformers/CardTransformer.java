package com.example.namma_bazaar_ecommerce.transformers;

import com.example.namma_bazaar_ecommerce.dto.requestDto.CardRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CardResponseDto;
import com.example.namma_bazaar_ecommerce.model.Card;
import com.example.namma_bazaar_ecommerce.model.Customer;
import com.example.namma_bazaar_ecommerce.repository.CustomerRepository;

public class CardTransformer {
    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card
                .builder()
                .expiryDate(cardRequestDto.getExpiryDate())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .cardNo(cardRequestDto.getCardNo())
                .build();
    }


}

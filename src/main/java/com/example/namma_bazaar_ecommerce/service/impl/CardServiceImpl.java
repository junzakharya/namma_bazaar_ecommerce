package com.example.namma_bazaar_ecommerce.service.impl;

import com.example.namma_bazaar_ecommerce.dto.requestDto.CardRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CardResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidCustomerException;
import com.example.namma_bazaar_ecommerce.model.Card;
import com.example.namma_bazaar_ecommerce.model.Customer;
import com.example.namma_bazaar_ecommerce.repository.CustomerRepository;
import com.example.namma_bazaar_ecommerce.service.CardService;
import com.example.namma_bazaar_ecommerce.transformers.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {
        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());
        if(customer==null){
            throw new InvalidCustomerException("Sorry! The customer doesn't exists");
        }

        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        customerRepository.save(customer);

        // response dto
        return CardResponseDto.builder().message("congrats "+customer.getName()+" your card has been added")
                .build();
    }
}

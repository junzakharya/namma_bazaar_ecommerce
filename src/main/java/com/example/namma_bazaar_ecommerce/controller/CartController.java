package com.example.namma_bazaar_ecommerce.controller;

import com.example.namma_bazaar_ecommerce.dto.requestDto.CheckoutCartRequestDto;
import com.example.namma_bazaar_ecommerce.dto.requestDto.ItemRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CartResponseDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.OrderResponseDto;
import com.example.namma_bazaar_ecommerce.model.Item;
import com.example.namma_bazaar_ecommerce.service.impl.CartServiceImpl;
import com.example.namma_bazaar_ecommerce.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemServiceImpl itemService;

    @Autowired
    CartServiceImpl cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception {

        try{
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(),savedItem);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        return cartService.checkOutCart(checkoutCartRequestDto);
    }

}

package com.example.namma_bazaar_ecommerce.transformers;

import com.example.namma_bazaar_ecommerce.dto.responseDto.CartResponseDto;
import com.example.namma_bazaar_ecommerce.model.Cart;

public class CartTransformer {

    public static CartResponseDto cartToCartResponseDto(Cart cart){
        return CartResponseDto
                .builder()
                .numberOfItems(cart.getNumberOfItems())
                .cartTotal(cart.getCartTotal())
                .build();
    }
}

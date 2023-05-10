package com.example.namma_bazaar_ecommerce.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    String productName;

    int priceOfOneItem;

    int totalPrice;

    int quantity;
}

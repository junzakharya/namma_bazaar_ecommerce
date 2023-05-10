package com.example.namma_bazaar_ecommerce.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CheckoutCartRequestDto {
    int customerId;

    String cardNo;

    int cvv;
}

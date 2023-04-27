package com.example.namma_bazaar_ecommerce.dto.requestDto;

import com.example.namma_bazaar_ecommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {
    String mobNo;
    String cardNo;

    int cvv;

    Date expiryDate;

    CardType cardType;
}

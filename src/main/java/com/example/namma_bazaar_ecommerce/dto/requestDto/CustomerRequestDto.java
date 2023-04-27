package com.example.namma_bazaar_ecommerce.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CustomerRequestDto {
    String name;

    int age;

    String emailId;

    String mobNo;

    String address;
}

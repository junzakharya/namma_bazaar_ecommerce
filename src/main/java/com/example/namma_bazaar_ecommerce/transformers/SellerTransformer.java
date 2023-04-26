package com.example.namma_bazaar_ecommerce.transformers;

import com.example.namma_bazaar_ecommerce.dto.requestDto.SellerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.SellerResponseDto;
import com.example.namma_bazaar_ecommerce.model.Seller;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

public class SellerTransformer {
    public static Seller sellerRequestdtotoSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();

    }

    public static SellerResponseDto sellerToSellerResponsedto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName()).age(seller.getAge())
                .build();
    }
}

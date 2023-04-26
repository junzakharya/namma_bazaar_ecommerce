package com.example.namma_bazaar_ecommerce.transformers;

import com.example.namma_bazaar_ecommerce.Enum.ProductStatus;
import com.example.namma_bazaar_ecommerce.dto.requestDto.ProductRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.ProductResponseDto;
import com.example.namma_bazaar_ecommerce.model.Product;

public class ProductTransformer {
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getProductName())
                .productStatus(ProductStatus.AVAILABLE)
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .emailId(product.getSeller().getEmailId())
                .build();
    }
}

package com.example.namma_bazaar_ecommerce.service;

import com.example.namma_bazaar_ecommerce.Enum.ProductCategory;
import com.example.namma_bazaar_ecommerce.dto.requestDto.ProductRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.ProductResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidSellerException;
import com.example.namma_bazaar_ecommerce.model.Item;

import java.util.List;

public interface ProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    public List<ProductResponseDto> getProductByCategory(ProductCategory productCategory);

    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String productCategory);

    public void decreaseProductQuantity(Item item) throws Exception;
}

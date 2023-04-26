package com.example.namma_bazaar_ecommerce.controller;

import com.example.namma_bazaar_ecommerce.Enum.ProductCategory;
import com.example.namma_bazaar_ecommerce.dto.requestDto.ProductRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.ProductResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidSellerException;
import com.example.namma_bazaar_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {
       return productService.addProduct(productRequestDto);
    }

    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getProductByCategory(@PathVariable("category") ProductCategory productCategory){
        return productService.getProductByCategory(productCategory);
    }
}

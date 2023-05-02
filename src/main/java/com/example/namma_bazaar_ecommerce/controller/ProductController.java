package com.example.namma_bazaar_ecommerce.controller;

import com.example.namma_bazaar_ecommerce.Enum.ProductCategory;
import com.example.namma_bazaar_ecommerce.dto.requestDto.ProductRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.ProductResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidSellerException;
import com.example.namma_bazaar_ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @PostMapping("/add") // to add a product for a seller
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {
       return productService.addProduct(productRequestDto);
    }

    @GetMapping("/get/{category}") //to get product based on category
    public List<ProductResponseDto> getProductByCategory(@PathVariable("category") ProductCategory productCategory){
        return productService.getProductByCategory(productCategory);
    }

    @GetMapping("get/{price}/{category}") // to get all the product based on category and price
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(@PathVariable("price") int price,
                                                                     @PathVariable("category") String productCategory){
        return productService.getAllProductsByPriceAndCategory(price, productCategory);
    }
}

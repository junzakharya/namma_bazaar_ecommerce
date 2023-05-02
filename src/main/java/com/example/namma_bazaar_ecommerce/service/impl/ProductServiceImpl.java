package com.example.namma_bazaar_ecommerce.service.impl;

import com.example.namma_bazaar_ecommerce.Enum.ProductCategory;
import com.example.namma_bazaar_ecommerce.dto.requestDto.ProductRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.ProductResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidSellerException;
import com.example.namma_bazaar_ecommerce.model.Product;
import com.example.namma_bazaar_ecommerce.model.Seller;
import com.example.namma_bazaar_ecommerce.repository.ProductRepository;
import com.example.namma_bazaar_ecommerce.repository.SellerRepository;
import com.example.namma_bazaar_ecommerce.service.ProductService;
import com.example.namma_bazaar_ecommerce.transformers.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new InvalidSellerException("seller doesn't exist");
        }

        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProductList().add(product);

        sellerRepository.save(seller);

        return ProductTransformer.productToProductResponseDto(product);
    }

    public List<ProductResponseDto> getProductByCategory(ProductCategory productCategory){
        List<Product> products = productRepository.findByProductCategory(productCategory);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product:products){
            productResponseDtos.add(ProductTransformer.productToProductResponseDto(product));
        }
        return productResponseDtos;
    }
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String productCategory){
        List<Product> products = productRepository.getAllProductsByPriceAndCategory(price, productCategory);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product p: products){
            productResponseDtos.add(ProductTransformer.productToProductResponseDto(p));
        }
        return productResponseDtos;
    }

}

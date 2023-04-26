package com.example.namma_bazaar_ecommerce.service;

import com.example.namma_bazaar_ecommerce.Enum.ProductCategory;
import com.example.namma_bazaar_ecommerce.dto.requestDto.SellerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.ProductResponseDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.SellerResponseDto;
import com.example.namma_bazaar_ecommerce.exception.EmailAlreadyExistsException;
import com.example.namma_bazaar_ecommerce.model.Product;
import com.example.namma_bazaar_ecommerce.model.Seller;
import com.example.namma_bazaar_ecommerce.repository.SellerRepository;
import com.example.namma_bazaar_ecommerce.transformers.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyExistsException {
        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId())!=null){
            throw new EmailAlreadyExistsException("User already Exists");
        }

        Seller seller = SellerTransformer.sellerRequestdtotoSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResponsedto(savedSeller);
    }

}

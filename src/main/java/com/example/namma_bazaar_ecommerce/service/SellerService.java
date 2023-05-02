package com.example.namma_bazaar_ecommerce.service;

import com.example.namma_bazaar_ecommerce.dto.requestDto.SellerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.SellerResponseDto;
import com.example.namma_bazaar_ecommerce.exception.EmailAlreadyExistsException;
import com.example.namma_bazaar_ecommerce.exception.InvalidSellerException;

import java.util.List;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyExistsException;

    public SellerResponseDto getSellerByEmail(String emailId) throws InvalidSellerException;

    public List<SellerResponseDto> getAllSeller();

    public SellerResponseDto updateInfo(String email, SellerRequestDto sellerRequestDto) throws InvalidSellerException;

    public String deleteSeller(String emailId) throws InvalidSellerException;
}

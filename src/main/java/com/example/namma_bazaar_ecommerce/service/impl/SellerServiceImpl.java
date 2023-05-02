package com.example.namma_bazaar_ecommerce.service.impl;

import com.example.namma_bazaar_ecommerce.dto.requestDto.SellerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.SellerResponseDto;
import com.example.namma_bazaar_ecommerce.exception.EmailAlreadyExistsException;
import com.example.namma_bazaar_ecommerce.exception.InvalidSellerException;
import com.example.namma_bazaar_ecommerce.model.Seller;
import com.example.namma_bazaar_ecommerce.repository.SellerRepository;
import com.example.namma_bazaar_ecommerce.service.SellerService;
import com.example.namma_bazaar_ecommerce.transformers.SellerTransformer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {

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

    @Override
    public SellerResponseDto getSellerByEmail(String emailId) throws InvalidSellerException {

        Seller seller;
        try{
           seller = sellerRepository.findByEmailId(emailId);
        }
        catch (Exception e){
            throw new InvalidSellerException("invalid emailId");
        }

        return SellerTransformer.sellerToSellerResponsedto(seller);
    }

    @Override
    public List<SellerResponseDto> getAllSeller() {
        List<Seller> sellers = sellerRepository.getAllSeller();

        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();

        for(Seller seller: sellers){
            sellerResponseDtos.add(SellerTransformer.sellerToSellerResponsedto(seller));
        }
        return sellerResponseDtos;
    }

    @Override
    public SellerResponseDto updateInfo(String email, SellerRequestDto sellerRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller = sellerRepository.findByEmailId(email);
        }
        catch (Exception e){
            throw new InvalidSellerException("invalid email");
        }
        seller.setName(sellerRequestDto.getName());
        seller.setAge(sellerRequestDto.getAge());
        seller.setEmailId(sellerRequestDto.getEmailId());
        seller.setMobNo(sellerRequestDto.getMobNo());

        sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResponsedto(seller);
    }

    @Override
    public String deleteSeller(String emailId) throws InvalidSellerException {
        Seller seller;
        try {
            seller = sellerRepository.findByEmailId(emailId);
        } catch (Exception e) {
            throw new InvalidSellerException("seller doesn't exist");
        }
        sellerRepository.deleteByEmailId(emailId);

        return "deleted successfully";
    }

}

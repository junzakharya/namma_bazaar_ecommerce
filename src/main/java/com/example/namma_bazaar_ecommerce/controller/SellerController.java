package com.example.namma_bazaar_ecommerce.controller;

import com.example.namma_bazaar_ecommerce.dto.requestDto.SellerRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.SellerResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidSellerException;
import com.example.namma_bazaar_ecommerce.service.impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerServiceImpl sellerService;

    @PostMapping("/add") // to add a seller
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        try {
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByEmail") // find seller by email
    public SellerResponseDto getSellerByEmail(@RequestParam String emailId) throws InvalidSellerException {
       return sellerService.getSellerByEmail(emailId);
    }

    @GetMapping("/getAll") // gives all the sellers present
    public List<SellerResponseDto> getAllSeller(){
        return sellerService.getAllSeller();
    }

    @PutMapping("/upadateInfo") //updates seller's information
    public ResponseEntity updatePassword(@RequestParam String email, @RequestBody SellerRequestDto sellerRequestDto){
        try{
            SellerResponseDto sellerResponseDto = sellerService.updateInfo(email, sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        } catch (InvalidSellerException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete") //deletes seller from db
    public String deleteSeller(@RequestParam String emailId) throws InvalidSellerException {
        return sellerService.deleteSeller(emailId);
    }

}

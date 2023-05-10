package com.example.namma_bazaar_ecommerce.service;

import com.example.namma_bazaar_ecommerce.dto.requestDto.ItemRequestDto;
import com.example.namma_bazaar_ecommerce.model.Item;

public interface ItemService {
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}

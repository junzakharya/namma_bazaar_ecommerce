package com.example.namma_bazaar_ecommerce.transformers;

import com.example.namma_bazaar_ecommerce.dto.responseDto.OrderResponseDto;
import com.example.namma_bazaar_ecommerce.model.Ordered;
import jakarta.persistence.criteria.Order;

public class OrderTransformer {

    public static OrderResponseDto orderToOrderResponseDto(Ordered ordered){
        return OrderResponseDto
                .builder()
                .totalValue(ordered.getTotalValue())
                .customerName(ordered.getCustomer().getName())
                .cardUsed(ordered.getCardUsed())
                .orderNo(ordered.getOrderNumber())
                .orderDate(ordered.getOrderDate())
                .build();
    }
}

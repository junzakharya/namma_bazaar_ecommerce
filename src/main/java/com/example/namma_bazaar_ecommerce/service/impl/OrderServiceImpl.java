package com.example.namma_bazaar_ecommerce.service.impl;

import com.example.namma_bazaar_ecommerce.dto.requestDto.OrderRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.ItemResponseDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.OrderResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidCardException;
import com.example.namma_bazaar_ecommerce.exception.InvalidCustomerException;
import com.example.namma_bazaar_ecommerce.exception.InvalidProductException;
import com.example.namma_bazaar_ecommerce.model.*;
import com.example.namma_bazaar_ecommerce.repository.CardRepository;
import com.example.namma_bazaar_ecommerce.repository.CustomerRepository;
import com.example.namma_bazaar_ecommerce.repository.OrderedRepository;
import com.example.namma_bazaar_ecommerce.repository.ProductRepository;
import com.example.namma_bazaar_ecommerce.service.OrderService;
import com.example.namma_bazaar_ecommerce.service.ProductService;
import com.example.namma_bazaar_ecommerce.transformers.ItemTransformer;
import com.example.namma_bazaar_ecommerce.transformers.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    private OrderedRepository orderedRepository;
    @Autowired
    private JavaMailSender emailSender;

    @Override //to place order from the Cart
    public Ordered placeOrder(Customer customer, Card card) throws Exception {
        Cart cart = customer.getCart();

        Ordered order = new Ordered();
        order.setOrderNumber(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item: cart.getItemList()){
            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            } catch (Exception e) {
                throw new Exception("Product Out of stock");
            }
        }
        order.setItemList(orderedItems);
        for(Item item: orderedItems)
            item.setOrdered(order);
        order.setTotalValue(cart.getCartTotal());

        return order;
    }

    @Override // to directly place order without cart
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new InvalidProductException("Product doesn't exist");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());

        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();

        try{
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

        Ordered order = new Ordered();
        order.setOrderNumber(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity()*product.getPrice());
        order.getItemList().add(item);

        customer.getOrderedList().add(order);

        item.setOrdered(order);

        product.getItemList().add(item);

        Ordered savedOrder = orderedRepository.save(order);

        OrderResponseDto orderResponseDto = OrderTransformer.orderToOrderResponseDto(savedOrder);

        List<ItemResponseDto> items = new ArrayList<>();

        for(Item itemEntity: savedOrder.getItemList()){

            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);

            items.add(itemResponseDto);
        }

        orderResponseDto.setItems(items);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("acciopracticemail.com");
        message.setTo(customer.getEmailId());
        message.setSubject("order placed");
        message.setText("hello "+ customer.getName()+" your order with order number "+ order.getOrderNumber()+" has been placed");
        emailSender.send(message);

        return orderResponseDto;
    }


    @Override
    public String generateMaskedCard(String cardNo){
        String maskedCardNo = "";
        for(int i = 0;i<cardNo.length()-4;i++)
            maskedCardNo += 'X';
        maskedCardNo += cardNo.substring(cardNo.length()-4);
        return maskedCardNo;

    }
}

package com.example.namma_bazaar_ecommerce.service.impl;

import com.example.namma_bazaar_ecommerce.dto.requestDto.CheckoutCartRequestDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.CartResponseDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.ItemResponseDto;
import com.example.namma_bazaar_ecommerce.dto.responseDto.OrderResponseDto;
import com.example.namma_bazaar_ecommerce.exception.InvalidCardException;
import com.example.namma_bazaar_ecommerce.exception.InvalidCustomerException;
import com.example.namma_bazaar_ecommerce.model.*;
import com.example.namma_bazaar_ecommerce.repository.CardRepository;
import com.example.namma_bazaar_ecommerce.repository.CartRepository;
import com.example.namma_bazaar_ecommerce.repository.CustomerRepository;
import com.example.namma_bazaar_ecommerce.repository.OrderedRepository;
import com.example.namma_bazaar_ecommerce.service.CartService;
import com.example.namma_bazaar_ecommerce.service.OrderService;
import com.example.namma_bazaar_ecommerce.transformers.CartTransformer;
import com.example.namma_bazaar_ecommerce.transformers.ItemTransformer;
import com.example.namma_bazaar_ecommerce.transformers.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public CartResponseDto saveCart(Integer customerId, Item item) {
        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getCartTotal() + item.getRequiredQuantity()*item.getProduct().getPrice();

        cart.setCartTotal(newTotal);
        cart.getItemList().add(item);
        cart.setNumberOfItems(cart.getItemList().size());

        item.setCart(cart);

        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartTransformer.cartToCartResponseDto(savedCart);
        cartResponseDto.setCustomerName(customer.getName());

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item itemEntity: savedCart.getItemList()){
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);
            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;

    }



    @Override
    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer id is invalid!!!");
        }

        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if(card==null || card.getCvv()!=checkoutCartRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0){
            throw new Exception("Cart is empty!!");
        }

        try{
            Ordered order = orderService.placeOrder(customer,card);  // throw exception if product goes out of stock
            customer.getOrderedList().add(order);
            resetCart(cart);
            Ordered savedOrder = orderedRepository.save(order);

            // prepare response dto
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
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private void resetCart(Cart cart) {
        cart.setCartTotal(0);
        for(Item item: cart.getItemList()){
            item.setCart(null);
        }
        cart.setNumberOfItems(0);
        cart.getItemList().clear();

    }
}


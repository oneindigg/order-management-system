package com.nikesh.dto.order;

import com.nikesh.models.Customer;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private int deliveryCharge;
    private int discounts;
    private String paymentMethod;
    private Customer customer;
    private List<OrderItemRequest> items;
    private String status;
}

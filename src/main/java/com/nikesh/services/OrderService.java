package com.nikesh.services;

import com.nikesh.dto.order.CreateOrderRequest;
import com.nikesh.dto.order.OrderDto;
import com.nikesh.dto.order.SingleOrderDto;
import com.nikesh.models.Order;

import java.util.List;

public interface OrderService {

    List<OrderDto> list();
    OrderDto create(CreateOrderRequest request);
    OrderDto update(Long id, CreateOrderRequest request);
    SingleOrderDto getById(Long id);

    List<OrderDto> getByStatus(String status);
    void delete(Long id);
}

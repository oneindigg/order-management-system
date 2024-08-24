package com.nikesh.services.implementations;

import com.nikesh.dto.order.*;
import com.nikesh.exceptions.ResourceNotFoundException;
import com.nikesh.mappers.OrderMapper;
import com.nikesh.models.Order;
import com.nikesh.models.OrderItem;
import com.nikesh.models.Product;
import com.nikesh.repositories.OrderRepository;
import com.nikesh.repositories.ProductRepository;
import com.nikesh.services.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    @Override
    public List<OrderDto> list() {
        List<Order> orders = this.orderRepository.findAll();
        return orderMapper.orderDtoList(orders);
    }

    @Override
    public OrderDto create(CreateOrderRequest request) {
        Order order = new Order();
        order.setCustomer(request.getCustomer());
        order.setDiscounts(request.getDiscounts());
        order.setDeliveryCharge(request.getDeliveryCharge());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setSlug(request.getCustomer().getName().toLowerCase()+request.getCustomer().getEmail().toLowerCase());
        order.setStatus("PENDING");

        Set<OrderItem> orderItems = new HashSet<>();
        for (OrderItemRequest itemRequest: request.getItems()){
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("No such product found!"));
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);

            product.setQuantity(product.getQuantity() - itemRequest.getQuantity());
        }
        order.setItems(orderItems);
        order.setTotalPrice(order.getTotalOrderPrice());

       return orderMapper.orderToDto(orderRepository.save(order));

    }

    @Override
    public OrderDto update(Long id, CreateOrderRequest request) {
        Order exOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such order found!"));
        exOrder.setCustomer(request.getCustomer());
        exOrder.setPaymentMethod(request.getPaymentMethod());
        exOrder.setDiscounts(request.getDiscounts());
        exOrder.setDeliveryCharge(request.getDeliveryCharge());
        exOrder.setPaymentMethod(request.getPaymentMethod());
        exOrder.setSlug(request.getCustomer().getName().toLowerCase()+request.getCustomer().getEmail().toLowerCase());
        exOrder.setStatus(request.getStatus());

        Set<OrderItem> orderItems = new HashSet<>();
        for(OrderItemRequest itemRequest : request.getItems()){
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("No such product found!"));
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setOrder(exOrder);
            orderItems.add(orderItem);
        }

        exOrder.setItems(orderItems);
        exOrder.setTotalPrice(exOrder.getTotalOrderPrice());
        return orderMapper.orderToDto(orderRepository.save(exOrder));

    }

    @Override
    public SingleOrderDto getById(Long id) {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such order found"));
        return orderMapper.orderToSingleDto(order);
    }

    @Override
    public List<OrderDto> getByStatus(String status) {
        System.out.println(status + "============================");
        List<Order> order = orderRepository.getOrderByStatus(status);
        System.out.println(order + "------------------");
        return orderMapper.orderDtoList(order);
    }

    @Override
    public void delete(Long id) {
//        Order order = this.getById(id);
//        for(Product product: order.getProducts()){
//            order.removeOrder(product);
//        }
//        this.orderRepository.deleteById(id);
    }
}

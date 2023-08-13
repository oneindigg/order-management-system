package com.nikesh.mappers;

import com.nikesh.dto.order.OrderItemDto;
import com.nikesh.dto.order.OrderDto;
import com.nikesh.dto.order.ProductDto;
import com.nikesh.dto.order.SingleOrderDto;
import com.nikesh.models.Order;
import com.nikesh.models.OrderItem;
import com.nikesh.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    @Mappings({
            @Mapping(source = "deliveryCharge", target = "deliveryCharge"),
            @Mapping(source = "items", target = "items"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "totalPrice", target = "productTotalPrice"),
            @Mapping(source = "totalQuantity", target = "productTotalQuantity"),
            @Mapping(source = "paymentMethod", target = "paymentMethod")
    })
    SingleOrderDto orderToSingleDto(Order order);

    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "customer.email", target = "customerEmail")
    @Mapping(source = "totalPrice", target = "productTotalPrice")
    @Mapping(source = "totalQuantity", target="productTotalQuantity")
    OrderDto orderToDto(Order order);
    List<OrderDto> orderDtoList(List<Order> order);

    @Mapping(source = "product", target = "product")
    OrderItemDto orderItemToDto(OrderItem orderItem);

    @Mapping(source = "product", target = "product")
    OrderItem dtoToOrderItem(OrderItemDto dto);

    @Mapping(source = "sellingPrice", target = "price")
    ProductDto productToDto(Product product);

    Product dtoToProduct(ProductDto dto);

}

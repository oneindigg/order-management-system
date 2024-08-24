package com.nikesh.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_order")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int deliveryCharge;
    private int discounts;
    private String paymentMethod;
    private int totalPrice;
    private int totalQuantity;
    private String status;
    private String slug;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "customer_name")),
            @AttributeOverride(name = "email", column = @Column(name = "customer_email")),
            @AttributeOverride(name = "number", column = @Column(name = "customer_number")),
            @AttributeOverride(name = "address", column = @Column(name = "customer_address")),
            @AttributeOverride(name = "province", column = @Column(name = "customer_province")),
            @AttributeOverride(name = "city", column = @Column(name = "customer_city")),
            @AttributeOverride(name = "area", column = @Column(name = "customer_area")),
    })
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<OrderItem> items = new HashSet<>();


    public int getTotalOrderPrice(){
        int sum = 0;
        Set<OrderItem> orderItems = getItems();
        for(OrderItem item : orderItems){
            sum += item.getProduct().getSellingPrice() * item.getQuantity();
        }
        return sum - getDiscounts();
    }

    public int getTotalQuantity(){
        int totalQuantity = 0;
        Set<OrderItem> orderItems = getItems();
        for(OrderItem item : orderItems){
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

//    public void removeOrder(Product product){
//        this.products.remove(product);
//    }
}

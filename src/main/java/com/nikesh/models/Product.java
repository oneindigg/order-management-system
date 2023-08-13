package com.nikesh.models;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer sellingPrice;
    private Integer costPerItem;
    private Integer quantity;
    private String productSku;
    private String productImageUrl;
    private String color;
    private String size;

    @ManyToMany(cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "tbl_product_categories",
        joinColumns = {
            @JoinColumn(name = "product_id")
        },
        inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<Category> categories = new HashSet<>();

    public void removeProduct(Category category){
        this.categories.remove(category);
        category.getProducts().remove(this);
    }
}

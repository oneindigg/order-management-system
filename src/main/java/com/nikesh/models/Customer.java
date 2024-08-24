package com.nikesh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    private String name;
    private String email;
    private String number;
    private String address;
    private String province;
    private String city;
    private String area;
}

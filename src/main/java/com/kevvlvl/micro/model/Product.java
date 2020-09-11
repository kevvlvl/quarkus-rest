package com.kevvlvl.micro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(
        name = "Product.findAll",
        query = "SELECT p FROM Product p ORDER BY p.name"
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    private int id;

    private String name;
    private String description;
    private double price;
}

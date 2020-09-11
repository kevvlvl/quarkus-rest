package com.kevvlvl.micro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private int id;
    private String name;
    private String description;
    private double price;
}

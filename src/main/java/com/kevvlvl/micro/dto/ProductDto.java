package com.kevvlvl.micro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private double price;
}

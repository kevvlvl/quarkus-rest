package com.kevvlvl.micro.service;

import com.kevvlvl.micro.model.Product;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Slf4j
@RequestScoped
public class ProductService {

    @Getter
    private Set<Product> products;

    public ProductService() {
        products = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        products.add(new Product("Laptop", "Thinkpad T480", 1200.50));
        products.add(new Product("Chair", "Fancy Pantsy chair", 500.25));

        log.info("ProductService initialized");
    }
}

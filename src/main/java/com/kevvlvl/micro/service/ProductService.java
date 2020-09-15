package com.kevvlvl.micro.service;

import com.kevvlvl.micro.dto.ProductDto;
import com.kevvlvl.micro.model.Product;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Slf4j
@RequestScoped
public class ProductService {

    private EntityManager em;
    private Validator validator;

    @Inject
    public ProductService(EntityManager em, Validator validator) {
        this.em = em;
        this.validator = validator;
    }

    @Transactional
    public int createProduct(ProductDto dto) {

        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());

        Set<ConstraintViolation<Product>> violations = validator.validate(p);

        if(violations.isEmpty()) {
            em.persist(p);
            log.info("createProduct() - Persisted entity {}", p);
        }
        else {
            log.error("createProduct() - Failed to validate product!");
            violations.stream().forEach(v -> log.error(" - violation: " + v.getMessage()));
        }

        return p.getId();
    }

    public List<Product> getProducts() {
        return em.createNamedQuery("Product.findAll", Product.class)
                .getResultList();
    }
}

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
import java.util.stream.Collectors;

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
    public ProductDto saveProduct(ProductDto dto) {

        log.info("saveProduct() - Received dto {}", dto);
        Product p = null;

        if(dto.getId() == null) {
            p = save(mapToEntity(dto), false);
            log.info("saveProduct() - Created Product {} ", p);
        }
        else {
            p = save(mapToEntity(dto), true);
            log.info("saveProduct() - Updated Product {} ", p);
        }

        dto.setId(p.getId());

        return dto;
    }

    public List<ProductDto> getProducts() {

        List<Product> products = em.createNamedQuery("Product.findAll", Product.class)
                .getResultList();

        return products
                .stream()
                .map(p -> mapToDto(p))
                .collect(Collectors.toList());
    }

    private Product save(Product p, boolean isUpdate) {

        if(p != null) {
            Set<ConstraintViolation<Product>> violations = validator.validate(p);

            if (violations.isEmpty()) {

                if(isUpdate) {
                    p = em.merge(p);
                    log.info("save() - Merged/Updated entity {}", p);
                }
                else {
                    em.persist(p);
                    log.info("save() - Persisted entity {}", p);
                }

            } else {
                log.error("save() - Failed to validate product!");
                violations.stream().forEach(v -> log.error(" - violation: " + v.getMessage()));
            }
        }

        return p;
    }

    private Product mapToEntity(ProductDto dto) {

        Product p = null;

        if(dto != null) {

            p = new Product();
            p.setName(dto.getName());
            p.setDescription(dto.getDescription());
            p.setPrice(dto.getPrice());
        }

        return p;
    }

    private ProductDto mapToDto(Product p) {

        ProductDto dto = null;

        if(p != null) {

            dto = new ProductDto();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setDescription(p.getDescription());
            dto.setPrice(p.getPrice());
        }

        return dto;
    }
}

package com.kevvlvl.micro.service;

import com.kevvlvl.micro.dto.ProductDto;
import com.kevvlvl.micro.model.Product;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequestScoped
public class ProductService {

    private EntityManager em;

    @Inject
    public ProductService(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public int createProduct(ProductDto dto) {

        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());

        em.persist(p);

        log.info("createProduct() - Persisted entity {}", p);

        return p.getId();
    }

    public List<Product> getProducts() {
        return em.createNamedQuery("Product.findAll", Product.class)
                .getResultList();
    }
}

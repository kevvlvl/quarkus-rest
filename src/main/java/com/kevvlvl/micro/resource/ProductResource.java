package com.kevvlvl.micro.resource;

import com.kevvlvl.micro.model.Product;
import com.kevvlvl.micro.service.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GET
    public Set<Product> list() {
        return this.productService.getProducts();
    }

    @POST
    public Set<Product> add(Product p) {
        this.productService.getProducts().add(p);
        return this.productService.getProducts();
    }
}

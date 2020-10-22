package com.kevvlvl.micro.resource;

import com.kevvlvl.micro.dto.ProductDto;
import com.kevvlvl.micro.service.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GET
    public List<ProductDto> list() {
        return this.productService.getProducts();
    }

    @POST
    public int add(ProductDto dto) {
        dto = this.productService.saveProduct(dto);
        return dto.getId();
    }
}

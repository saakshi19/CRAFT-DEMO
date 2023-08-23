package org.example.product.controllers;

import org.example.product.models.Product;
import org.example.product.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product/v1/")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(
            value = "/{productId}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<Product> getProduct(
            @PathVariable("productId") final Long productId
    ) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @RequestMapping(
            value = "add",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<Product> addProduct(
            @RequestBody Product request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }
}

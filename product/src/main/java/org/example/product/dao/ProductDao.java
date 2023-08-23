package org.example.product.dao;

import org.example.product.models.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductDao {
    private final Map<Long, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProduct(Long productId) {
        return products.get(productId);
    }
}

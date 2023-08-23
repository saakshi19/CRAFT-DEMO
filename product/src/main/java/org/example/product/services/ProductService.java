package org.example.product.services;

import org.example.product.dao.ProductDao;
import org.example.product.models.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product getProduct(Long productId) {
        return productDao.getProduct(productId);
    }

    public Product addProduct(Product product) {
        productDao.addProduct(product);
        return product;
    }
}

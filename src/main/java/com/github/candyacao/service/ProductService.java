package com.github.candyacao.service;

import com.github.candyacao.model.Product;

import java.util.List;

public interface ProductService {
    String add(Product prodoct);
    Product getProductByID(Long id);
    String decProductCount(Product product);
    String deleteProduct(Long id);
    List<Product> getProducts();
    String updateProduct(Product product);
}

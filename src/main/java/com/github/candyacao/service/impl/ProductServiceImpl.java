package com.github.candyacao.service.impl;

import com.github.candyacao.exception.GlobleException;
import com.github.candyacao.mapper.ProductMapper;
import com.github.candyacao.model.Product;
import com.github.candyacao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.candyacao.common.enums.ResultStatus.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public String add(Product prodoct) {
        int count = productMapper.add(prodoct);
        if (count <= 0){
            throw new GlobleException(PRODUCT_ADD_FAIL);
        }
        return "ok";
    }

    @Override
    public Product getProductByID(Long id) {
        Product product = productMapper.getProductByID(id);
        if (null == product){
            throw new GlobleException(PRODUCT_ISNOT_EXSIT);
        }
        return product;
    }

    @Override
    public String decProductCount(Product product) {
       int count = productMapper.decProductCount(product);
       if(count <= 0){
           throw new GlobleException(PRODUCT_DEC_FAIL);
       }
        return "ok";
    }

    @Override
    public String deleteProduct(Long id) {
        int count = productMapper.deleteProduct(id);
        if(count <= 0){
            throw new GlobleException(PRODUCT_DELETE_FAIL);
        }
        return "ok";
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productMapper.getProducts();
        return products;
    }

    @Override
    public String updateProduct(Product product) {
        int count = productMapper.updateProduct(product);
        if(count <= 0){
            throw new GlobleException(PRODUCT_UPDATE_FAIL);
        }
        return "ok";
    }
}

package com.github.candyacao.service.impl;

import com.github.candyacao.exception.GlobleException;
import com.github.candyacao.mapper.ProductMapper;
import com.github.candyacao.model.Product;
import com.github.candyacao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.candyacao.common.enums.ResultStatus.PRODUCT_ISNOT_EXSIT;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public String add(Product prodoct) {
        productMapper.add(prodoct);
        return "商品添加成功";
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
    public String updateProduct(Product product) {
        productMapper.updateProduct(product);
        return "更新商品库存成功";
    }

    @Override
    public String deleteProduct(Long id) {
        productMapper.deleteProduct(id);
        return "删除成功";
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productMapper.getProducts();
        return products;
    }
}

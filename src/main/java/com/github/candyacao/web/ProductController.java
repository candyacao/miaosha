package com.github.candyacao.web;

import com.github.candyacao.common.resultbean.Result;
import com.github.candyacao.model.Product;
import com.github.candyacao.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    // 添加商品
    @PostMapping("/createProduct")
    public Result<String> createProduct(Product product){
        Result<String> result = Result.build();
        String productID = productService.add(product);
        result.setData(productID);
        return result;
    }
    @GetMapping("/getProductByID")
    public Result<Product> getProductByID(Long id){
        Result<Product> result = Result.build();
        Product product = productService.getProductByID(id);
        result.setData(product);
        return result;
    }
    // 减少商品库存
    @PutMapping("/decProductCount")
    public Result<String> decProductCount(Product product){
        Result<String> result = Result.build();
        String msg = productService.decProductCount(product);
        result.setData(msg);
        return result;
    }
    // 更改商品信息
    @PutMapping("/updateProduct")
    public Result<String> updateProduct(Product product){
        Result<String> result = Result.build();
        String msg = productService.updateProduct(product);
        result.setData(msg);
        return result;
    }
    // 删除商品
    @DeleteMapping("/deleteProduct")
    public Result<String> deleteProduct(Long id){
        Result<String> result = Result.build();
        String msg = productService.deleteProduct(id);
        result.setData(msg);
        return result;
    }
    // 获取商品列表
    @GetMapping("/getProducts")
    public Result<List<Product>> getProducts(){
        Result<List<Product>> result = Result.build();
        List<Product> products = productService.getProducts();
        result.setData(products);
        return result;
    }
}

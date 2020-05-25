package com.github.candyacao.web;

import com.github.candyacao.common.resultbean.Result;
import com.github.candyacao.model.Cart;
import com.github.candyacao.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/addCart")
    public Result<String> addCart(Cart cart){
        Result<String> result = Result.build();
        cartService.addCart(cart);
        result.setData("ok");
        return result;
    }

    @GetMapping("/getCarts")
    public Result<List<Cart>> getCarts(Cart cart){
        Result<List<Cart>> result = Result.build();
        List<Cart> carts = cartService.getCart(cart.getUserID());
        result.setData(carts);
        return result;
    }

    @PutMapping("/decCartCount")
    public Result<String> updateCart(Cart cart){
        Result<String> result = Result.build();
        cartService.decProductCount(cart);
        result.setData("ok");
        return result;
    }

    @DeleteMapping("/deleteCart")
    public Result<String> deleteCart(Cart cart){
        Result<String> result = Result.build();
        cartService.deleteCart(cart);
        result.setData("删除成功");
        return result;
    }
}

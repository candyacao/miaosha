package com.github.candyacao.web;

import com.github.candyacao.common.resultbean.Result;
import com.github.candyacao.model.Cart;
import com.github.candyacao.model.User;
import com.github.candyacao.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.github.candyacao.common.enums.ResultStatus.NEED_LOGIN;


@RestController
@Slf4j
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/addCart")
    public Result<String> addCart(HttpSession session, Long productID, int quantity){
        Result<String> result = Result.build();
        User user = (User) session.getAttribute(UserController.getSessionKey());
        if (user == null) {
            result.withError(NEED_LOGIN);
            return result;
        }
        cartService.addCart(user.getId(), productID, quantity);
        return result;
    }

    @GetMapping("/getCarts")
    public Result<List<Cart>> getCarts(HttpSession session){
        Result<List<Cart>> result = Result.build();
        User user = (User) session.getAttribute(UserController.getSessionKey());
        if (user == null) {
            result.withError(NEED_LOGIN);
            return result;
        }
        List<Cart> carts = cartService.getCart(user.getId());
        result.setData(carts);
        return result;
    }

    @PutMapping("/decCartCount")
    public Result<String> updateCart(Cart cart){
        Result<String> result = Result.build();
        cartService.decProductCount(cart);
        return result;
    }

    @DeleteMapping("/deleteCart")
    public Result<String> deleteCart(Cart cart){
        Result<String> result = Result.build();
        cartService.deleteCart(cart);
        return result;
    }
}

package com.github.candyacao.web;

import com.github.candyacao.common.resultbean.Result;
import com.github.candyacao.model.Order;
import com.github.candyacao.model.User;
import com.github.candyacao.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.github.candyacao.common.enums.ResultStatus.NEED_LOGIN;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public Result<String> createOrder(HttpSession session, Long productID, Integer quantity) {
        Result<String> result = Result.build();
        User user = (User) session.getAttribute(UserController.getSessionKey());
        if (user == null) {
            result.withError(NEED_LOGIN);
            return result;
        }
        orderService.createOrder(user.getId(), productID, quantity);
        return result;
    }

    @DeleteMapping("/deleteOrder")
    public Result<String> deleteOrder(Long id) {
        Result<String> result = Result.build();
        orderService.deleteOrder(id);
        return result;
    }

    @GetMapping("/getOrderByID")
    public Result<Order> getOrderByID(Long id) {
        Result<Order> result = Result.build();
        Order order = orderService.getOrderByID(id);
        result.setData(order);
        return result;
    }

    @GetMapping("/getOrdersByUserID")
    public Result<List<Order>> getOrdersByUserID(HttpSession session) {
        Result<List<Order>> result = Result.build();
        User user = (User) session.getAttribute(UserController.getSessionKey());
        if (user == null) {
            result.withError(NEED_LOGIN);
            return result;
        }
        List<Order> orders = orderService.getOrdersByUserID(user.getId());
        result.setData(orders);
        return result;
    }
}

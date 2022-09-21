package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.service.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderHistory")
@AllArgsConstructor
public class OrderHistoryController {
    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> getOrderHistory(
            @AuthenticationPrincipal User user
    ){
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Order> orders = orderService.findByUser(user);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}

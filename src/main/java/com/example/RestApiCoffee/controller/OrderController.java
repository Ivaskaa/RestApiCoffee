package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.order.OrderDto;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.service.order.OrderService;
import com.example.RestApiCoffee.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> saveOrder(
            @AuthenticationPrincipal User user,
            @RequestBody OrderDto orderDto){
        HttpHeaders headers = new HttpHeaders();
        if(orderDto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Order order = orderDto.build();
        order.setUser(user);
        orderService.save(order);
        Integer price = orderService.getPrice(order);
        userService.addPoints(user, price);
        return new ResponseEntity<>(orderDto, headers, HttpStatus.CREATED);
    }
}

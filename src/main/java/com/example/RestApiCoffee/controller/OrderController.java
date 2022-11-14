package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.dto.requests.order.OrderRequest;
import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.order.OrderDto;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.service.order.OrderService;
import com.example.RestApiCoffee.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> saveOrder(
            @RequestBody OrderRequest orderRequest
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());
        if(orderRequest == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Order order = (Order) orderService.buildOrder(orderRequest);
            order.setUser(user);
            System.out.println(order);
            Integer points = orderService.getPrice(order);
            userService.addPoints(user, points);
            orderService.save(order);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception ignored){
            Map<String, String> exceptions = (Map<String, String>) orderService.buildOrder(orderRequest);
            return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
        }
    }
}

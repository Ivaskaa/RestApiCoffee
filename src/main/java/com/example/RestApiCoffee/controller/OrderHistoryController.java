package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.service.UserService;
import com.example.RestApiCoffee.service.order.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class OrderHistoryController {
    private final OrderService orderService;
    private final UserService userService;

    @ApiOperation(value = "Get order history", notes = "Get user order history")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Order history list is empty")
    })
    @GetMapping("/orderHistory")
    public ResponseEntity<List<Order>> getOrderHistory(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());

        List<Order> orders = orderService.findByUser(user);

        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}

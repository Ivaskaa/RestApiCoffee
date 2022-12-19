package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.dto.requests.order.OrderRequest;
import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.order.OrderDto;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.service.order.OrderService;
import com.example.RestApiCoffee.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @ApiOperation(value = "Create order", notes = "Create order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created - Successfully created"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly")
    })
    @PostMapping()
    public ResponseEntity<?> saveOrder(
            @Valid @RequestBody OrderRequest orderRequest,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByPhone(authentication.getName());

        Map<String, String> messages = orderService.buildOrder(orderRequest, user);
        if(messages.containsKey("order")){
            return new ResponseEntity<>(messages, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
        }
    }
}

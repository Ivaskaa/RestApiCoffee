package com.example.RestApiCoffee.controller;


import com.example.RestApiCoffee.dto.requests.Award;
import com.example.RestApiCoffee.dto.responses.MessageResponse;
import com.example.RestApiCoffee.entities.AwardsDto;
import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.order.coffee.CoffeeOrder;
import com.example.RestApiCoffee.entities.order.dessert.DessertOrder;
import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import com.example.RestApiCoffee.entities.order.snack.SnackOrder;
import com.example.RestApiCoffee.entities.order.tea.TeaOrder;
import com.example.RestApiCoffee.entities.user.User;
import com.example.RestApiCoffee.service.order.*;
import com.example.RestApiCoffee.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/awards")
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class AwardsController {
    private final OrderService orderService;
    private final UserService userService;
    private final CoffeeOrderService coffeeOrderService;
    private final DessertOrderService dessertOrderService;
    private final SandwichOrderService sandwichOrderService;
    private final SnackOrderService snackOrderService;
    private final TeaOrderService teaOrderService;

    @ApiOperation(value = "Get all awards", notes = "Get all created active rewards")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Awards was not created")
    })
    @GetMapping()
    public ResponseEntity<AwardsDto> getAllAwards(){
        Order order = orderService.findById(1L);

        if(order == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AwardsDto awardsDto = order.buildAwards();

        return new ResponseEntity<>(awardsDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Get coffee award by points", notes = "Get an award from the coffee section (you must input coffee size id, count of points you can see in profile))")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Dont have coffee in awards or dont have coffee award with selected id"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - Not enough points")
    })
    @PostMapping("/coffee")
    public ResponseEntity<?> coffee(
            @Valid @RequestBody Award award,
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
        Order order = orderService.findById(1L);

        if(order.getCoffeeOrder() != null) {
            for (CoffeeOrder coffeeOrder : order.getCoffeeOrder()) {
                if (award.getId() == coffeeOrder.getSize().getId()) {
                    if (userService.subtractPoint(user, coffeeOrderService.getPrice(coffeeOrder))) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Not enough points"), HttpStatus.UNPROCESSABLE_ENTITY);
                    }
                }
            }
            return new ResponseEntity<>(new MessageResponse("Dont have coffee award with this id"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new MessageResponse("Dont have coffee in awards"), HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "Get dessert award by points", notes = "Get an award from the dessert section (you must input dessert size id, count of points you can see in profile)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly"),
            @ApiResponse(code = 204, message = "No Content - Dont have dessert in awards or dont have dessert award with selected id"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - Not enough points")
    })
    @PostMapping("/dessert")
    public ResponseEntity<?> dessert(
            @Valid @RequestBody Award award,
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
        Order order = orderService.findById(1L);

        if(order.getDessertOrder() != null) {
            for (DessertOrder dessertOrder : order.getDessertOrder()) {
                if (award.getId() == dessertOrder.getSize().getId()) {
                    if (userService.subtractPoint(user, dessertOrderService.getPrice(dessertOrder))) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Not enough points"), HttpStatus.UNPROCESSABLE_ENTITY);
                    }
                }
            }
            return new ResponseEntity<>(new MessageResponse("Dont have dessert award with this id"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new MessageResponse("Dont have dessert in awards"), HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "Get sandwich award by points", notes = "Get an award from the sandwich section (you must input sandwich size id, count of points you can see in profile)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly"),
            @ApiResponse(code = 204, message = "No Content - Dont have sandwich in awards or dont have sandwich award with selected id"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - Not enough points")
    })
    @PostMapping("/sandwich")
    public ResponseEntity<?> sandwich(
            @Valid @RequestBody Award award,
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
        Order order = orderService.findById(1L);

        if(order.getSandwichOrder() != null) {
            for (SandwichOrder sandwichOrder : order.getSandwichOrder()) {
                if (award.getId() == sandwichOrder.getSize().getId()) {
                    if (userService.subtractPoint(user, sandwichOrderService.getPrice(sandwichOrder))) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Not enough points"), HttpStatus.UNPROCESSABLE_ENTITY);
                    }
                }
            }
            return new ResponseEntity<>(new MessageResponse("Dont have sandwich award with this id"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new MessageResponse("Dont have sandwich in awards"), HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "Get snack award by points", notes = "Get an award from the snack section (you must input snack size id, count of points you can see in profile)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly"),
            @ApiResponse(code = 204, message = "No Content - Dont have snack in awards or dont have snack award with selected id"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - Not enough points")
    })
    @PostMapping("/snack")
    public ResponseEntity<?> snack(
            @Valid @RequestBody Award award,
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
        Order order = orderService.findById(1L);

        if(order.getSnackOrder() != null) {
            for (SnackOrder snackOrder : order.getSnackOrder()) {
                if (award.getId() == snackOrder.getSize().getId()) {
                    if (userService.subtractPoint(user, snackOrderService.getPrice(snackOrder))) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Not enough points"), HttpStatus.UNPROCESSABLE_ENTITY);
                    }
                }
            }
            return new ResponseEntity<>(new MessageResponse("Dont have snack award with this id"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new MessageResponse("Dont have snack in awards"), HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "Get tea award by points", notes = "Get an award from the tea section (you must tea snack size id, count of points you can see in profile)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly"),
            @ApiResponse(code = 204, message = "No Content - Dont have tea in awards or dont have tea award with selected id"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - Not enough points")
    })
    @PostMapping("/tea")
    public ResponseEntity<?> tea(
            @Valid @RequestBody Award award,
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
        Order order = orderService.findById(1L);

        if(order.getTeaOrder() != null) {
            for (TeaOrder teaOrder : order.getTeaOrder()) {
                if (award.getId() == teaOrder.getSize().getId()) {
                    if (userService.subtractPoint(user, teaOrderService.getPrice(teaOrder))) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Not enough points"), HttpStatus.UNPROCESSABLE_ENTITY);
                    }
                }
            }
            return new ResponseEntity<>(new MessageResponse("Dont have tea award with this id"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new MessageResponse("Dont have tea in awards"), HttpStatus.NO_CONTENT);
        }
    }
}

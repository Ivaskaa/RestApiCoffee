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
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/awards")
@AllArgsConstructor
public class AwardsController {
    private final OrderService orderService;
    private final UserService userService;
    private final CoffeeOrderService coffeeOrderService;
    private final DessertOrderService dessertOrderService;
    private final SandwichOrderService sandwichOrderService;
    private final SnackOrderService snackOrderService;
    private final TeaOrderService teaOrderService;


    @GetMapping()
    public ResponseEntity<AwardsDto> getAllAwards(){
        Order order = orderService.findById(1L);

        if(order == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AwardsDto awardsDto = order.buildAwards();

        return new ResponseEntity<>(awardsDto, HttpStatus.OK);
    }

    @PostMapping("/coffee")
    public ResponseEntity<?> coffee(
            @AuthenticationPrincipal User user,
            @RequestBody Award award
    ){
        Order order = orderService.findById(1L);

        if(award == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(order.getCoffeeOrder() != null) {
            for (CoffeeOrder coffeeOrder : order.getCoffeeOrder()) {
                if (award.getId() == coffeeOrder.getSize().getId()) {
                    if (userService.subtractPoint(user, coffeeOrderService.getPrice(coffeeOrder))) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Not enough points"), HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Dont have coffee in awards"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/dessert")
    public ResponseEntity<?> dessert(
            @AuthenticationPrincipal User user,
            @RequestBody Award award
    ){
        Order order = orderService.findById(1L);

        if(award == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(order.getDessertOrder() != null) {
            for (DessertOrder dessertOrder : order.getDessertOrder()) {
                if (award.getId() == dessertOrder.getSize().getId()) {
                    if (userService.subtractPoint(user, dessertOrderService.getPrice(dessertOrder))) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Not enough points"), HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Dont have dessert in awards"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/sandwich")
    public ResponseEntity<?> sandwich(
            @AuthenticationPrincipal User user,
            @RequestBody Award award
    ){
        Order order = orderService.findById(1L);

        if(award == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(order.getSandwichOrder() != null) {
            for (SandwichOrder sandwichOrder : order.getSandwichOrder()) {
                if (award.getId() == sandwichOrder.getSize().getId()) {
                    if (userService.subtractPoint(user, sandwichOrderService.getPrice(sandwichOrder))) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Not enough points"), HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Dont have sandwich in awards"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/snack")
    public ResponseEntity<?> snack(
            @AuthenticationPrincipal User user,
            @RequestBody Award award
    ){
        Order order = orderService.findById(1L);

        if(award == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(order.getSnackOrder() != null) {
            for(SnackOrder snackOrder : order.getSnackOrder()){
                if(award.getId() == snackOrder.getSize().getId()){
                    if (userService.subtractPoint(user, snackOrderService.getPrice(snackOrder))) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Not enough points"), HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Dont have snack in awards"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/tea")
    public ResponseEntity<?> tea(
            @AuthenticationPrincipal User user,
            @RequestBody Award award
    ){
        Order order = orderService.findById(1L);

        if(award == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(order.getTeaOrder() != null) {
            for(TeaOrder teaOrder : order.getTeaOrder()){
                if(award.getId() == teaOrder.getSize().getId()){
                    if (userService.subtractPoint(user, teaOrderService.getPrice(teaOrder))) {
                        return new ResponseEntity<>(HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Not enough points"), HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Dont have tea in awards"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

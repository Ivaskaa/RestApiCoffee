package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.dto.responses.MessageResponse;
import com.example.RestApiCoffee.entities.product.coffee.Coffee;
import com.example.RestApiCoffee.entities.product.dessert.Dessert;
import com.example.RestApiCoffee.entities.product.sandwich.Sandwich;
import com.example.RestApiCoffee.entities.product.snack.Snack;
import com.example.RestApiCoffee.entities.product.tea.Tea;
import com.example.RestApiCoffee.service.product.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class ProductController {
    private final CoffeeService coffeeService;
    private final DessertService dessertService;
    private final SandwichService sandwichService;
    private final SnackService snackService;
    private final TeaService teaService;

    @ApiOperation(value = "Get all coffee products", notes = "Get all active coffee products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Coffee list is empty")
    })
    @GetMapping("/coffee")
    public ResponseEntity<List<Coffee>> getAllActiveCoffees(){
        List<Coffee> coffees = coffeeService.findAllActive();

        if(coffees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all dessert products", notes = "Get all active dessert products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Dessert list is empty")
    })
    @GetMapping("/dessert")
    public ResponseEntity<List<Dessert>> getAllActiveDesserts(){
        List<Dessert> desserts = dessertService.findAllActive();

        System.out.println(desserts);

        if(desserts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(desserts, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all sandwich products", notes = "Get all active sandwich products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Sandwich list is empty")
    })
    @GetMapping("/sandwich")
    public ResponseEntity<List<Sandwich>> getAllActiveSandwiches(){
        List<Sandwich> sandwiches = sandwichService.findAllActive();

        if(sandwiches.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sandwiches, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all snack products", notes = "Get all active snack products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Snack list is empty")
    })
    @GetMapping("/snack")
    public ResponseEntity<List<Snack>> getAllActiveSnacks(){
        List<Snack> snacks = snackService.findAllActive();

        if(snacks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(snacks, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all tea products", notes = "Get all active tea products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Tea list is empty")
    })
    @GetMapping("/tea")
    public ResponseEntity<List<Tea>> getAllActiveTeas(){
        List<Tea> teas = teaService.findAllActive();

        if(teas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(teas, HttpStatus.OK);
    }

}

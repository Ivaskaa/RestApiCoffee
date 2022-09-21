package com.example.RestApiCoffee.controller.menu;

import com.example.RestApiCoffee.entities.product.coffee.Coffee;
import com.example.RestApiCoffee.service.product.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coffee")
@AllArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping()
    public ResponseEntity<List<Coffee>> getAllLocations(){
        List<Coffee> coffees = coffeeService.findAllActive();

        if(coffees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }
}

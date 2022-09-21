package com.example.RestApiCoffee.controller.menu;

import com.example.RestApiCoffee.entities.product.sandwich.Sandwich;
import com.example.RestApiCoffee.service.product.SandwichService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sandwich")
@AllArgsConstructor
public class SandwichController {
    private final SandwichService sandwichService;

    @GetMapping()
    public ResponseEntity<List<Sandwich>> getAllLocations(){
        List<Sandwich> sandwiches = sandwichService.findAllActive();

        if(sandwiches.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sandwiches, HttpStatus.OK);
    }
}

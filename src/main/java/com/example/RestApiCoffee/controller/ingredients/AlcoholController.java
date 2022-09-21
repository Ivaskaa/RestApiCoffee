package com.example.RestApiCoffee.controller.ingredients;

import com.example.RestApiCoffee.entities.ingredients.alcohol.Alcohol;
import com.example.RestApiCoffee.entities.product.coffee.Coffee;
import com.example.RestApiCoffee.service.ingredient.AlcoholService;
import com.example.RestApiCoffee.service.product.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alcohol")
@AllArgsConstructor
public class AlcoholController {

    private final AlcoholService alcoholService;

    @GetMapping()
    public ResponseEntity<List<Alcohol>> getAllLocations(){
        List<Alcohol> alcohol = alcoholService.findAllActive();

        if(alcohol.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(alcohol, HttpStatus.OK);
    }
}

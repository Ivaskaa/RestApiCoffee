package com.example.RestApiCoffee.controller.ingredients;

import com.example.RestApiCoffee.entities.ingredients.alcohol.Alcohol;
import com.example.RestApiCoffee.entities.ingredients.milk.Milk;
import com.example.RestApiCoffee.service.ingredient.AlcoholService;
import com.example.RestApiCoffee.service.ingredient.MilkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/milk")
@AllArgsConstructor
public class MilkController {
    private final MilkService milkService;

    @GetMapping()
    public ResponseEntity<List<Milk>> getAllLocations(){
        List<Milk> milks = milkService.findAllActive();

        if(milks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(milks, HttpStatus.OK);
    }
}

package com.example.RestApiCoffee.controller.ingredients;

import com.example.RestApiCoffee.entities.ingredients.sauce.Sauce;
import com.example.RestApiCoffee.service.ingredient.SauceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sauce")
@AllArgsConstructor
public class SauceController {
    private final SauceService sauceService;

    @GetMapping()
    public ResponseEntity<List<Sauce>> getAllLocations(){
        List<Sauce> sauces = sauceService.findAllActive();

        if(sauces.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(sauces, HttpStatus.OK);
    }
}

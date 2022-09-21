package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.city.City;
import com.example.RestApiCoffee.entities.product.snack.Snack;
import com.example.RestApiCoffee.service.CityService;
import com.example.RestApiCoffee.service.product.SnackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
@AllArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping()
    public ResponseEntity<List<City>> getAllCities(){
        List<City> cities = cityService.findAllActive();

        if(cities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}

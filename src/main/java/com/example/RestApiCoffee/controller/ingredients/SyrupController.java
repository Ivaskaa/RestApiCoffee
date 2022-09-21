package com.example.RestApiCoffee.controller.ingredients;

import com.example.RestApiCoffee.entities.ingredients.syrup.Syrup;
import com.example.RestApiCoffee.service.ingredient.SyrupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/syrup")
@AllArgsConstructor
public class SyrupController {
    private final SyrupService syrupService;

    @GetMapping()
    public ResponseEntity<List<Syrup>> getAllLocations(){
        List<Syrup> syrups = syrupService.findAllActive();

        if(syrups.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(syrups, HttpStatus.OK);
    }
}

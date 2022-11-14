package com.example.RestApiCoffee.controller.menu;

import com.example.RestApiCoffee.entities.product.dessert.Dessert;
import com.example.RestApiCoffee.service.product.DessertService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dessert")
@AllArgsConstructor
public class DessertController {

    private final DessertService dessertService;

    @GetMapping()
    public ResponseEntity<List<Dessert>> getAllDesserts(){
        List<Dessert> desserts = dessertService.findAllActive();

        System.out.println(desserts);

        if(desserts.isEmpty()){
            System.out.println("hello");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(desserts, HttpStatus.OK);
    }
}

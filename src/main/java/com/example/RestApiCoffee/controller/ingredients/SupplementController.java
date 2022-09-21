package com.example.RestApiCoffee.controller.ingredients;

import com.example.RestApiCoffee.entities.ingredients.supplement.Supplement;
import com.example.RestApiCoffee.service.ingredient.SupplementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplement")
@AllArgsConstructor
public class SupplementController {
    private final SupplementService supplementService;

    @GetMapping()
    public ResponseEntity<List<Supplement>> getAllLocations(){
        List<Supplement> supplements = supplementService.findAllActive();

        if(supplements.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(supplements, HttpStatus.OK);
    }
}

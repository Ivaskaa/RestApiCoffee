package com.example.RestApiCoffee.controller.menu;

import com.example.RestApiCoffee.entities.product.snack.Snack;
import com.example.RestApiCoffee.service.product.SnackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/snack")
@AllArgsConstructor
public class SnackController {
    private final SnackService snackService;

    @GetMapping()
    public ResponseEntity<List<Snack>> getAllSnacks(){
        List<Snack> snacks = snackService.findAllActive();

        if(snacks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(snacks, HttpStatus.OK);
    }
}

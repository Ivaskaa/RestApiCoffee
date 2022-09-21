package com.example.RestApiCoffee.controller.menu;

import com.example.RestApiCoffee.entities.product.tea.Tea;
import com.example.RestApiCoffee.service.product.TeaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tea")
@AllArgsConstructor
public class TeaController {
    private final TeaService teaService;

    @GetMapping()
    public ResponseEntity<List<Tea>> getAllLocations(){
        List<Tea> teas = teaService.findAllActive();

        if(teas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(teas, HttpStatus.OK);
    }
}

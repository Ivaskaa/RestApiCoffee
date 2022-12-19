package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.city.City;
import com.example.RestApiCoffee.entities.product.snack.Snack;
import com.example.RestApiCoffee.service.CityService;
import com.example.RestApiCoffee.service.product.SnackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class CityController {
    private final CityService cityService;

    @ApiOperation(value = "Get all cities", notes = "Get all active cities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - City list is empty")
    })
    @GetMapping()
    public ResponseEntity<List<City>> getAllCities(){
        List<City> cities = cityService.findAllActive();

        if(cities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}

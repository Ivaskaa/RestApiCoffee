package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.ingredients.alcohol.Alcohol;
import com.example.RestApiCoffee.entities.ingredients.milk.Milk;
import com.example.RestApiCoffee.entities.ingredients.sauce.Sauce;
import com.example.RestApiCoffee.entities.ingredients.supplement.Supplement;
import com.example.RestApiCoffee.entities.ingredients.syrup.Syrup;
import com.example.RestApiCoffee.service.ingredient.*;
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
@RequestMapping("/api/v1/ingredients")
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class IngredientController {
    private final AlcoholService alcoholService;
    private final MilkService milkService;
    private final SauceService sauceService;
    private final SupplementService supplementService;
    private final SyrupService syrupService;

    @ApiOperation(value = "Get all alcohol ingredients", notes = "Get all active alcohol ingredients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Alcohol list is empty")
    })
    @GetMapping("/alcohol")
    public ResponseEntity<List<Alcohol>> getAllActiveAlcohols(){
        List<Alcohol> alcohol = alcoholService.findAllActive();

        if(alcohol.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(alcohol, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all milk ingredients", notes = "Get all active milk ingredients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Milk list is empty")
    })
    @GetMapping("/milk")
    public ResponseEntity<List<Milk>> getAllActiveMilks(){
        List<Milk> milks = milkService.findAllActive();

        if(milks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(milks, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all sauce ingredients", notes = "Get all active sauce ingredients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Sauce list is empty")
    })
    @GetMapping("/sauce")
    public ResponseEntity<List<Sauce>> getAllActiveSauces(){
        List<Sauce> sauces = sauceService.findAllActive();

        if(sauces.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sauces, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all supplement ingredients", notes = "Get all active supplement ingredients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Supplement list is empty")
    })
    @GetMapping("/supplement")
    public ResponseEntity<List<Supplement>> getAllActiveSupplements(){
        List<Supplement> supplements = supplementService.findAllActive();

        if(supplements.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(supplements, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all syrup ingredients", notes = "Get all active syrup ingredients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Syrup list is empty")
    })
    @GetMapping("/syrup")
    public ResponseEntity<List<Syrup>> getAllActiveSyrups(){
        List<Syrup> syrups = syrupService.findAllActive();

        if(syrups.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(syrups, HttpStatus.OK);
    }
}

package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.Location;
import com.example.RestApiCoffee.service.LocationService;
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
@RequestMapping("/api/v1/location")
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class LocationController {
    private final LocationService locationService;

    @ApiOperation(value = "Get all locations", notes = "Get all active locations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Location list is empty")
    })
    @GetMapping()
    public ResponseEntity<List<Location>> getAllLocations(){
        List<Location> locations = locationService.findAllActive();

        if(locations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}

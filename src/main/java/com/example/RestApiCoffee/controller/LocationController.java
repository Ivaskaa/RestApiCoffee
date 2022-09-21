package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.Education;
import com.example.RestApiCoffee.entities.Location;
import com.example.RestApiCoffee.service.EducationService;
import com.example.RestApiCoffee.service.LocationService;
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
public class LocationController {
    private final LocationService locationService;

    @GetMapping()
    public ResponseEntity<List<Location>> getAllLocations(){
        List<Location> locations = locationService.findAllActive();

        if(locations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}

package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.Education;
import com.example.RestApiCoffee.service.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/education")
@AllArgsConstructor
public class EducationController {
    private final EducationService educationService;

    @GetMapping()
    public ResponseEntity<List<Education>> getAllEducation(){
        List<Education> educations = educationService.findAllActive();

        if(educations == null || educations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(educations, HttpStatus.OK);
    }
}

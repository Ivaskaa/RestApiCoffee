package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.Education;
import com.example.RestApiCoffee.service.EducationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class EducationController {
    private final EducationService educationService;

    @ApiOperation(value = "Get all educations", notes = "Get all active educations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Education list is empty")
    })
    @GetMapping("/education")
    public ResponseEntity<List<Education>> getAllEducation(){
        List<Education> educations = educationService.findAllActive();

        if(educations == null || educations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(educations, HttpStatus.OK);
    }
}

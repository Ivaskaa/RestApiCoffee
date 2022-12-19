package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.PrivacyPolicy;
import com.example.RestApiCoffee.repository.PrivacyPoliciesRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/privacyPolicy")
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class PrivacyPoliciesController {
    private final PrivacyPoliciesRepository policiesRepository;

    @ApiOperation(value = "Get privacy policy", notes = "Get privacy policy")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Privacy policy form is empty")
    })
    @GetMapping()
    public ResponseEntity<PrivacyPolicy> getAllLocations(){
        PrivacyPolicy privacyPolicy = policiesRepository.findById(1L).orElse(null);

        if(privacyPolicy == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(privacyPolicy, HttpStatus.OK);
    }
}

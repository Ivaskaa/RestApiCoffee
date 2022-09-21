package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.PrivacyPolicy;
import com.example.RestApiCoffee.repository.PrivacyPoliciesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/privacyPolicy")
@AllArgsConstructor
public class PrivacyPoliciesController {
    private final PrivacyPoliciesRepository policiesRepository;

    @GetMapping()
    public ResponseEntity<PrivacyPolicy> getAllLocations(){
        PrivacyPolicy privacyPolicy = policiesRepository.findById(1L).orElse(null);

        if(privacyPolicy == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(privacyPolicy, HttpStatus.OK);
    }
}

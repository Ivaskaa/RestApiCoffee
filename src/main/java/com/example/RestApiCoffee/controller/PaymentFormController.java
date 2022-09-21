package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.PaymentForm;
import com.example.RestApiCoffee.repository.PaymentFormRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paymentForm")
@AllArgsConstructor
public class PaymentFormController {
    private final PaymentFormRepository paymentFormRepository;

    @GetMapping()
    public ResponseEntity<List<PaymentForm>> getAllLocations(){
        List<PaymentForm> paymentForms = paymentFormRepository.findAll();

        if(paymentForms.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(paymentForms, HttpStatus.OK);
    }
}

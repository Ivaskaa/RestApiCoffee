package com.example.RestApiCoffee.controller;

import com.example.RestApiCoffee.entities.PaymentForm;
import com.example.RestApiCoffee.repository.PaymentFormRepository;
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
@RequestMapping("/api/v1")
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class PaymentFormController {
    private final PaymentFormRepository paymentFormRepository;

    @ApiOperation(value = "Get payment forms", notes = "Get all payment forms")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - Successfully received"),
            @ApiResponse(code = 204, message = "No Content - Payment form list is empty")
    })
    @GetMapping("/paymentForm")
    public ResponseEntity<List<PaymentForm>> getAllPaymentForms(){
        List<PaymentForm> paymentForms = paymentFormRepository.findAll();

        if(paymentForms.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(paymentForms, HttpStatus.OK);
    }
}

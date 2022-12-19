package com.example.RestApiCoffee.controller;


import com.example.RestApiCoffee.dto.requests.FeedbackDto;
import com.example.RestApiCoffee.dto.responses.MessageResponse;
import com.example.RestApiCoffee.entities.Feedback;
import com.example.RestApiCoffee.service.FeedbackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server Error - Internal Server Error"),
        @ApiResponse(code = 401, message = "Unauthorized - Bearer token not valid")
})
public class FeedbackController {
    private final FeedbackService feedbackService;

    @ApiOperation(value = "Create feedback", notes = "Create feedback")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created - Feedback successfully created"),
            @ApiResponse(code = 400, message = "Bad Request - The fields are filled incorrectly")
    })
    @PostMapping("/feedback")
    public ResponseEntity<Object> saveFeedback(
            @Valid @RequestBody FeedbackDto feedbackDto,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        feedbackService.save(feedbackDto.build());

        return new ResponseEntity<>(new MessageResponse("Feedback successfully created"), HttpStatus.CREATED);
    }
}

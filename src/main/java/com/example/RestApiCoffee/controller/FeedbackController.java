package com.example.RestApiCoffee.controller;


import com.example.RestApiCoffee.entities.Feedback;
import com.example.RestApiCoffee.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/feedback")
@AllArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping()
    public ResponseEntity<Feedback> saveFeedback(@RequestBody Feedback feedback){
        System.out.println(feedback);
        if(feedback == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        feedbackService.save(feedback);

        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }
}

package com.example.RestApiCoffee.service;

import com.example.RestApiCoffee.entities.Feedback;
import com.example.RestApiCoffee.repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public Feedback save(Feedback feedback) {
        log.info("save feedback: {}", feedback);
        feedback.setStatus("Unwatched");
        feedbackRepository.save(feedback);
        log.info("success");
        return feedback;
    }
}

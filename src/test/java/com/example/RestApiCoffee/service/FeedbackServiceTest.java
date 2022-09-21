package com.example.RestApiCoffee.service;

import com.example.RestApiCoffee.entities.Feedback;
import com.example.RestApiCoffee.repository.FeedbackRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class FeedbackServiceTest {
    @Autowired
    private FeedbackService feedbackService;
    @MockBean
    private FeedbackRepository feedbackRepository;
    @Test
    void save() {
        Feedback feedback = new Feedback();
        feedback.setName("name");
        feedback.setEmail("email");
        feedback.setPhone("phone");

        Feedback checkedFeedback = feedbackService.save(feedback);
        Assertions.assertEquals(checkedFeedback.getEmail(), feedback.getEmail());
        Assertions.assertEquals(checkedFeedback.getStatus(), "Unwatched");
        Mockito.verify(feedbackRepository, Mockito.times(1)).save(feedback);
    }
}
package com.example.RestApiCoffee.service;

import com.example.RestApiCoffee.entities.Education;
import com.example.RestApiCoffee.repository.EducationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
@SpringBootTest
class EducationServiceTest {
    @Autowired
    private EducationService educationService;
    @MockBean
    private EducationRepository educationRepository;

    @Test
    void findAllActive() {
        List<Education> educations = educationService.findAllActive();
        Assertions.assertNotNull(educations);
        Mockito.verify(educationRepository, Mockito.times(1)).findAllActive();

    }
}
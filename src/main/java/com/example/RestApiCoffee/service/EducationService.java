package com.example.RestApiCoffee.service;

import com.example.RestApiCoffee.entities.Education;
import com.example.RestApiCoffee.repository.EducationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;

    public List<Education> findAllActive() {
        log.info("get all educations");
        List<Education> educations = educationRepository.findAllActive();
        log.info("success");
        return educations;
    }
}

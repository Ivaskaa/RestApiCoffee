package com.example.RestApiCoffee.service.ingredient;

import com.example.RestApiCoffee.entities.ingredients.alcohol.Alcohol;
import com.example.RestApiCoffee.repository.ingredients.AlcoholRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class AlcoholService {
    private final AlcoholRepository alcoholRepository;

    public List<Alcohol> findAllActive(){
        log.info("get all alcohols");
        List<Alcohol> alcohols = alcoholRepository.findAllActive();
        log.info("success");
        return alcohols;
    }
}

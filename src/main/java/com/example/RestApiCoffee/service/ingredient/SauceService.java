package com.example.RestApiCoffee.service.ingredient;

import com.example.RestApiCoffee.entities.ingredients.sauce.Sauce;
import com.example.RestApiCoffee.repository.ingredients.SauceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class SauceService {
    private final SauceRepository sauceRepository;

    public List<Sauce> findAllActive() {
        log.info("get all sauces");
        List<Sauce> sauces = sauceRepository.findAllActive();
        log.info("success");
        return sauces;
    }
}

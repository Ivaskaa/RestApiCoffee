package com.example.RestApiCoffee.service.ingredient;

import com.example.RestApiCoffee.entities.ingredients.syrup.Syrup;
import com.example.RestApiCoffee.repository.ingredients.SyrupRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class SyrupService {
    private final SyrupRepository syrupRepository;

    public List<Syrup> findAllActive() {
        log.info("get all syrups");
        List<Syrup> syrups = syrupRepository.findAllActive();
        log.info("success");
        return syrups;
    }
}

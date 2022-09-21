package com.example.RestApiCoffee.service.ingredient;

import com.example.RestApiCoffee.entities.ingredients.milk.Milk;
import com.example.RestApiCoffee.repository.ingredients.MilkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class MilkService {
    private final MilkRepository milkRepository;

    public List<Milk> findAllActive() {
        log.info("get all milks");
        List<Milk> milks = milkRepository.findAllActive();
        log.info("success");
        return milks;
    }

}

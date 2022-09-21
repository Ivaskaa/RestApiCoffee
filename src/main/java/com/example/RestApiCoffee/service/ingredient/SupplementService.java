package com.example.RestApiCoffee.service.ingredient;

import com.example.RestApiCoffee.entities.ingredients.supplement.Supplement;
import com.example.RestApiCoffee.repository.ingredients.SupplementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class SupplementService {
    private final SupplementRepository supplementRepository;

    public List<Supplement> findAllActive() {
        log.info("get all supplements");
        List<Supplement> supplements = supplementRepository.findAllActive();
        log.info("success");
        return supplements;
    }
}

package com.example.RestApiCoffee.service.product;

import com.example.RestApiCoffee.entities.product.sandwich.Sandwich;
import com.example.RestApiCoffee.repository.product.SandwichRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class SandwichService {
    private final SandwichRepository sandwichRepository;

    public List<Sandwich> findAllActive(){
        log.info("get all sandwiches");
        List<Sandwich> sandwiches = sandwichRepository.findAllActive();
        log.info("success");
        return sandwiches;
    }
}

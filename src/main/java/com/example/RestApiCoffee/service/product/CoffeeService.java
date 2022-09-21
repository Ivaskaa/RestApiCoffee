package com.example.RestApiCoffee.service.product;

import com.example.RestApiCoffee.entities.product.coffee.Coffee;
import com.example.RestApiCoffee.repository.product.CoffeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public List<Coffee> findAllActive(){
        log.info("get all coffees");
        List<Coffee> coffees = coffeeRepository.findAllActive();
        log.info("success");
        return coffees;
    }

}

package com.example.RestApiCoffee.service.product;

import com.example.RestApiCoffee.entities.product.dessert.Dessert;
import com.example.RestApiCoffee.repository.product.DessertRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class DessertService {
    private final DessertRepository dessertRepository;

    public List<Dessert> findAllActive(){
        log.info("get all desserts");
        List<Dessert> desserts = dessertRepository.findAllActive();
        log.info("success");
        return desserts;
    }

}

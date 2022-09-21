package com.example.RestApiCoffee.service.product;

import com.example.RestApiCoffee.entities.product.snack.Snack;
import com.example.RestApiCoffee.repository.product.SnackRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class SnackService {
    private final SnackRepository snackRepository;

    public List<Snack> findAllActive(){
        log.info("get all snacks");
        List<Snack> snacks = snackRepository.findAllActive();
        log.info("success");
        return snacks;
    }
}

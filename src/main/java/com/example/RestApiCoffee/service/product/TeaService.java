package com.example.RestApiCoffee.service.product;

import com.example.RestApiCoffee.entities.product.tea.Tea;
import com.example.RestApiCoffee.repository.product.TeaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class TeaService {
    private final TeaRepository teaRepository;

    public List<Tea> findAllActive(){
        log.info("get all teas");
        List<Tea> teas = teaRepository.findAllActive();
        log.info("success");
        return teas;
    }
}

package com.example.RestApiCoffee.service;


import com.example.RestApiCoffee.entities.city.City;
import com.example.RestApiCoffee.repository.CityRepository;
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
public class CityService {
    private final CityRepository cityRepository;

    public List<City> findAllActive() {
        log.info("get all cities");
        List<City> cities = cityRepository.findAllActive();
        log.info("success");
        return cities;
    }

    public City findById(Long id) {
        log.info("get city by id: {}", id);
        City city = cityRepository.findById(id).orElseThrow();
        log.info("success");
        return city;
    }
}
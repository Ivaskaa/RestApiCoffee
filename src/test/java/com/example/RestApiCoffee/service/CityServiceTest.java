package com.example.RestApiCoffee.service;

import com.example.RestApiCoffee.entities.city.City;
import com.example.RestApiCoffee.entities.product.tea.Tea;
import com.example.RestApiCoffee.repository.CityRepository;
import com.example.RestApiCoffee.repository.product.TeaRepository;
import com.example.RestApiCoffee.service.product.TeaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CityServiceTest {
    @Autowired
    private CityService cityService;
    @MockBean
    private CityRepository cityRepository;

    @Test
    void findAllActive() {
        List<City> cities = cityService.findAllActive();
        Assertions.assertNotNull(cities);
        Mockito.verify(cityRepository, Mockito.times(1)).findAllActive();
    }

    @Test
    void findById() {
        Mockito.doReturn(Optional.of(new City()))
                .when(cityRepository)
                .findById(1L);
        City city = cityService.findById(1L);
        Assertions.assertNotNull(city);
        Mockito.verify(cityRepository, Mockito.times(1)).findById(1L);
    }
}
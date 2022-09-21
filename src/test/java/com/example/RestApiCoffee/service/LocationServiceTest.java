package com.example.RestApiCoffee.service;

import com.example.RestApiCoffee.entities.Location;
import com.example.RestApiCoffee.repository.LocationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
@SpringBootTest
class LocationServiceTest {
    @Autowired
    private LocationService locationService;
    @MockBean
    private LocationRepository locationRepository;
    @Test
    void findAllActive() {
        List<Location> products = locationService.findAllActive();
        Assertions.assertNotNull(products);
        Mockito.verify(locationRepository, Mockito.times(1)).findAllActive();
    }
}
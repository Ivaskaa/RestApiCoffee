package com.example.RestApiCoffee.service;

import com.example.RestApiCoffee.entities.Location;
import com.example.RestApiCoffee.repository.LocationRepository;
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
public class LocationService {
    private final LocationRepository locationRepository;

    public List<Location> findAllActive() {
        log.info("get active locations");
        List<Location> locations = locationRepository.findAllActive();
        log.info("success");
        return locations;
    }
}

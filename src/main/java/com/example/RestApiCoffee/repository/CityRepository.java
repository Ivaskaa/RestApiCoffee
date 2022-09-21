package com.example.RestApiCoffee.repository;

import com.example.RestApiCoffee.entities.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where c.active = true")
    List<City> findAllActive();
}

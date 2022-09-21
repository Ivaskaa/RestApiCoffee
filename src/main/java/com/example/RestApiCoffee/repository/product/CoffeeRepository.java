package com.example.RestApiCoffee.repository.product;

import com.example.RestApiCoffee.entities.product.coffee.Coffee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    Page<Coffee> findAll(Pageable pageable);
    @Query("select c from Coffee c where c.active = true")
    List<Coffee> findAllActive();
}

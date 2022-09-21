package com.example.RestApiCoffee.repository.ingredients;

import com.example.RestApiCoffee.entities.ingredients.milk.Milk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilkRepository extends JpaRepository<Milk, Long> {
    Page<Milk> findAll(Pageable pageable);

    @Query("select m from Milk m where m.active = true")
    List<Milk> findAllActive();
}

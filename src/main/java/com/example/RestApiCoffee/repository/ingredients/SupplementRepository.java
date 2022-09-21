package com.example.RestApiCoffee.repository.ingredients;

import com.example.RestApiCoffee.entities.ingredients.supplement.Supplement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplementRepository extends JpaRepository<Supplement, Long> {
    Page<Supplement> findAll(Pageable pageable);
    @Query("select s from Supplement s where s.active = true")
    List<Supplement> findAllActive();
}

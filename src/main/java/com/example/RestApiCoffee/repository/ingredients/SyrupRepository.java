package com.example.RestApiCoffee.repository.ingredients;

import com.example.RestApiCoffee.entities.ingredients.syrup.Syrup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyrupRepository extends JpaRepository<Syrup, Long> {
    Page<Syrup> findAll(Pageable pageable);
    @Query("select s from Syrup s where s.active = true")
    List<Syrup> findAllActive();
}

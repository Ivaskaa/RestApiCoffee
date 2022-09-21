package com.example.RestApiCoffee.repository.product;

import com.example.RestApiCoffee.entities.product.snack.Snack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnackRepository extends JpaRepository<Snack, Long> {
    Page<Snack> findAll(Pageable pageable);
    @Query("select s from Snack s where s.active = true")
    List<Snack> findAllActive();
}

package com.example.RestApiCoffee.repository;
import com.example.RestApiCoffee.entities.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Page<Location> findAll(Pageable pageable);

    @Query("select l from Location l where l.active = true")
    List<Location> findAllActive();
}

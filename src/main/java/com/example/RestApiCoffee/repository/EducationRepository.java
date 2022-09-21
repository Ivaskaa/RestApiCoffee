package com.example.RestApiCoffee.repository;

import com.example.RestApiCoffee.entities.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    @Query("Select e from Education e where e.active = true")
    List<Education> findAllActive();
}

package com.example.RestApiCoffee.repository;

import com.example.RestApiCoffee.entities.PrivacyPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyPoliciesRepository extends JpaRepository<PrivacyPolicy, Long> {
}

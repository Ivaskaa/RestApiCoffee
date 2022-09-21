package com.example.RestApiCoffee.repository.orderDetails;

import com.example.RestApiCoffee.entities.order.sandwich.SandwichOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SandwichOrderRepository extends JpaRepository<SandwichOrder, Long> {
    @Query("select so from SandwichOrder so where so.order.id = :orderId")
    Page<SandwichOrder> findAllByOrderId(@Param("orderId") Long orderId, Pageable pageable);
}
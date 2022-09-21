package com.example.RestApiCoffee.repository.orderDetails;

import com.example.RestApiCoffee.entities.order.tea.TeaOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeaOrderRepository extends JpaRepository<TeaOrder, Long> {
    @Query("select to from TeaOrder to where to.order.id = :orderId")
    Page<TeaOrder> findAllByOrderId(@Param("orderId") Long orderId, Pageable pageable);
}

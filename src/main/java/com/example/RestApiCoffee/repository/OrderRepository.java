package com.example.RestApiCoffee.repository;

import com.example.RestApiCoffee.entities.order.Order;
import com.example.RestApiCoffee.entities.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Page<Order> findAll(Specification<Order> specification, Pageable pageable);

    List<Order> findAll();

    List<Order> findByUser(User user);

//    Page<Order> findAll(Pageable pageable);
//    @Query("select o from Order o where o.user.name like %?2% and o.id = ?1")
//    Page<Order> findByUserAndId(Long id, String name, Pageable pageable);
//    @Query("select o from Order o where o.user.name like %?1%")
//    Page<Order> findByUser(String name, Pageable pageable);
}

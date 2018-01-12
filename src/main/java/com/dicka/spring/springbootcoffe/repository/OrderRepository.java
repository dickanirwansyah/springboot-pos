package com.dicka.spring.springbootcoffe.repository;

import com.dicka.spring.springbootcoffe.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
}

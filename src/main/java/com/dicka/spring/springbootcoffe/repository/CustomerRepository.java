package com.dicka.spring.springbootcoffe.repository;

import com.dicka.spring.springbootcoffe.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
}

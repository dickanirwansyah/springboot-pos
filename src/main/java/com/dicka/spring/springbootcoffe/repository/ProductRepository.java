package com.dicka.spring.springbootcoffe.repository;

import com.dicka.spring.springbootcoffe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

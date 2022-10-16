package com.works.repositories;

import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductNameEqualsIgnoreCase(String productName);

    List<Product> findByProductNameContainsIgnoreCase(String productName);

}
package com.example.beststore.repository;

import com.example.beststore.models.ProductPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsPageRepository extends JpaRepository<ProductPage, Integer> {
    List<ProductPage> findByNameContainingIgnoreCase(String name);
}


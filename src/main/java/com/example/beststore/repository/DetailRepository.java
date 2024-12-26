package com.example.beststore.repository;

import com.example.beststore.models.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
    public interface DetailRepository extends JpaRepository< Detail, Long> {

}

package com.example.beststore.repository;

import com.example.beststore.models.Home;
import com.example.beststore.models.ProductPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
    List<ProductPage> findByNameContainingIgnoreCase(String name);
    // Bạn có thể thêm các phương thức tìm kiếm, tìm theo tên, hoặc các truy vấn khác nếu cần.
}

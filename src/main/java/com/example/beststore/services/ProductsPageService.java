package com.example.beststore.services;

import com.example.beststore.models.ProductPage;
import com.example.beststore.repository.ProductsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsPageService {

    @Autowired
    private ProductsPageRepository productsPageRepository;

    // Lấy tất cả sản phẩm
    public List<ProductPage> findAll() {
        return productsPageRepository.findAll();
    }

    // Tìm sản phẩm theo ID
    public ProductPage findById(int id) {
        Optional<ProductPage> productPage = productsPageRepository.findById(id);
        return productPage.orElse(null); // Nếu không tìm thấy thì trả về null
    }

    // Tạo sản phẩm mới
    public ProductPage create(ProductPage productPage) {
        return productsPageRepository.save(productPage); // Lưu vào cơ sở dữ liệu
    }

    // Lưu thông tin sản phẩm (Cập nhật)
    public ProductPage save(ProductPage productPage) {
        return productsPageRepository.save(productPage);
    }

    // Xóa sản phẩm theo ID
    public void delete(int id) {
        productsPageRepository.deleteById(id); // Xóa sản phẩm
    }
}

package com.example.beststore.controller;

import com.example.beststore.models.ProductPage;
import com.example.beststore.services.ProductsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiProductsPageController {

    @Autowired
    private ProductsPageService productsPageService;

    // Trả về danh sách sản phẩm
    @GetMapping("/product_page")
    public List<ProductPage> getProductList() {
        return productsPageService.findAll();  // Lấy danh sách tất cả sản phẩm từ service
    }

    // Trả về một sản phẩm cụ thể theo ID
    @GetMapping("/product_page/{id}")
    public ResponseEntity<ProductPage> getProductById(@PathVariable("id") int productId) {
        ProductPage productPage = productsPageService.findById(productId); // Sử dụng phương thức findById từ service
        if (productPage != null) {
            return ResponseEntity.status(200).body(productPage); // Nếu tìm thấy sản phẩm
        }
        return ResponseEntity.status(404).body(null); // Nếu không tìm thấy
    }

    // Xóa sản phẩm theo ID
    @DeleteMapping("/product_page/{id}")
    public ResponseEntity<List<ProductPage>> deleteProductById(@PathVariable("id") int productId) {
        productsPageService.delete(productId); // Xóa sản phẩm từ service
        return ResponseEntity.status(200).body(productsPageService.findAll()); // Trả về danh sách sản phẩm sau khi xóa
    }

    // Tạo sản phẩm mới
    @PostMapping("/product_page")
    public ResponseEntity<ProductPage> createProductById(@RequestBody ProductPage productPage) {
        ProductPage savedProduct = productsPageService.create(productPage); // Tạo mới sản phẩm từ service
        return ResponseEntity.status(201).body(savedProduct); // Trả về sản phẩm đã tạo với mã trạng thái 201
    }

    // Cập nhật thông tin sản phẩm
    @PutMapping("/product_page/{id}")
    public ResponseEntity<ProductPage> updateProductById(@PathVariable("id") int productId, @RequestBody ProductPage updateProduct) {
        ProductPage productPage = productsPageService.findById(productId); // Tìm sản phẩm theo ID từ service
        if (productPage != null) {
            // Cập nhật các trường
            productPage.setName(updateProduct.getName());
            productPage.setPrice(updateProduct.getPrice());
            productPage.setPrice1(updateProduct.getPrice1());
            productPage.setSales(updateProduct.getSales());
            productPage.setPercent(updateProduct.getPercent());
            productPage.setImageFilename(updateProduct.getImageFilename());
            productPage.setTitle(updateProduct.getTitle());
            productsPageService.save(productPage); // Lưu sản phẩm đã cập nhật
            return ResponseEntity.status(200).body(productPage); // Trả về sản phẩm đã cập nhật
        }
        return ResponseEntity.status(404).body(null); // Nếu không tìm thấy sản phẩm
    }
}

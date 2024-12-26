package com.example.beststore.services;

import com.example.beststore.models.ProductPage;
import com.example.beststore.repository.ProductsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductsPageRepository productsPageRepository;
    public ProductPage saveProduct(ProductPage productPage) {
        return productsPageRepository.save(productPage);
    }

    public List<ProductPage> finalAll(){
        return productsPageRepository.findAll();
    }
    public ProductPage findById(Integer id) {
        return productsPageRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }


    public ProductPage save(ProductPage productPage) {
        return productsPageRepository.save(productPage);
    }


    public ProductPage update(ProductPage productPage) {
        return productsPageRepository.save(productPage);
    }
    public void delete(Integer id) {
        productsPageRepository.deleteById(id);
    }

}



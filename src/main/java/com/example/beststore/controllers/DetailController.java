package com.example.beststore.controllers;

import com.example.beststore.models.Detail;
import com.example.beststore.models.ProductPage;
import com.example.beststore.services.DetailService;
import com.example.beststore.repository.ProductsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    private DetailService detailService;

    @Autowired
    private ProductsPageRepository repo;

    @GetMapping("/{id}")
    public String getDetail(@PathVariable("id") Long id, Model model) {
        Detail productPage = detailService.getDetailById(id);
        List<ProductPage> allProducts = repo.findAll();
        model.addAttribute("detail", productPage);

        model.addAttribute("productPage", allProducts);
        return "products/detail";  // Đảm bảo rằng file "detail.html" tồn tại trong thư mục templates
    }

}




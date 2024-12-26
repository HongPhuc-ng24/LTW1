package com.example.beststore.controllers;

import com.example.beststore.models.ProductPage;
import com.example.beststore.repository.ProductsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class AboutController {

    @Autowired
    private ProductsPageRepository repo;

    @GetMapping("/about")
    public String getAboutPage(Model model) {
        List<ProductPage> productPage = repo.findAll();
        model.addAttribute( "productPage", productPage);
        // Trả về tên tệp HTML trong thư mục templates
        return "products/about";
    }
    @GetMapping("/my_account")
    public String getMyAccountPage() {
        // Trả về tên tệp HTML trong thư mục templates
        return "products/my_account";
    }
}

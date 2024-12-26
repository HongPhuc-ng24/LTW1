package com.example.beststore.controllers;

import com.example.beststore.models.Home;
import com.example.beststore.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeRepository repo;

    @GetMapping({"", "/"})
    public String productPage(Model model) {
        List<Home> productPage = repo.findAll();
        model.addAttribute("productPage", productPage);
        return "products/home"; // For product_page.html in templates/products
    }
    @GetMapping("/search")
    public String searchProducts(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            model.addAttribute("productPage", repo.findByNameContainingIgnoreCase(name));
            model.addAttribute("searchKeyword", name); // Đưa từ khóa tìm kiếm vào model
        } else {
            model.addAttribute("productPage", repo.findAll()); // Hiển thị tất cả sản phẩm nếu từ khóa trống
        }
        return "products/search"; // Trả về view "search"
    }
}
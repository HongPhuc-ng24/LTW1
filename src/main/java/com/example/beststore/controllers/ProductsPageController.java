package com.example.beststore.controllers;

import com.example.beststore.models.ProductPage;
import com.example.beststore.repository.ProductsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/product_page")
public class ProductsPageController {

    @Autowired
    private ProductsPageRepository repo;


    @GetMapping({"", "/"})
    public String productPage(Model model) {
        List<ProductPage> productPage = repo.findAll();
        model.addAttribute("productPage", productPage);
        return "products/product_page";
        // For product_page.html in templates/products
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            var products = repo.findByNameContainingIgnoreCase(name);
            if (!products.isEmpty()) {
                model.addAttribute("productPage", products);
            } else {
                model.addAttribute("message", "Không tìm thấy sản phẩm nào với từ khóa: \"" + name + "\"");
            }
            model.addAttribute("searchKeyword", name); // Đưa từ khóa tìm kiếm vào model
        } else {
            model.addAttribute("productPage", Collections.emptyList()); // Trả về danh sách trống
            model.addAttribute("message", "Vui lòng nhập từ khóa tìm kiếm.");
        }
        return "products/search"; // Trả về view "search"
    }
}

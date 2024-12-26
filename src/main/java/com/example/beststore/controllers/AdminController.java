package com.example.beststore.controllers;

import com.example.beststore.models.ProductPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.beststore.repository.ProductsPageRepository;
import com.example.beststore.services.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductsPageRepository productRepository;

    @Autowired
    private ProductService productService;

    // Display list of products
    @GetMapping("/product_page")
    public String viewProducts(Model model) {
        model.addAttribute("productPage", productRepository.findAll()); // Add product list to model
        return "products/admin"; // View template for product list
    }

    // Show form to add a new product
    @GetMapping("/product_page/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("productPage", new ProductPage()); // Pass an empty product object to the form
        return "products/admin_add_product"; // View template for adding a product
    }

    // Save product to the database
    @PostMapping("/product_page/save")
    public String saveProduct(@ModelAttribute("productPage") ProductPage productPage) {
        productService.saveProduct(productPage); // Call the correct method to save the product
        return "redirect:/admin/product_page"; // Redirect to the product list page
    }

    // Show form to edit a product
    @GetMapping("/product_page/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
        ProductPage productPage = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")); // Handle product not found
        model.addAttribute("productPage", productPage); // Add the found product to the model
        return "products/editProduct"; // View template for editing a product
    }

    // Update product in the database
    @PostMapping("/product_page/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, @ModelAttribute("product") ProductPage productPage) {
        productPage.setId(id); // Ensure the product ID matches the one in the URL
        productRepository.save(productPage); // Save the updated product
        return "redirect:/admin/product_page"; // Redirect to product list page
    }

    // Delete a product
    @GetMapping("/product_page/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productRepository.deleteById(id); // Delete product by ID
        return "redirect:/admin/product_page"; // Redirect to product list page
    }
}

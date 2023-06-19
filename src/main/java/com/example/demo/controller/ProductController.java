package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.entity.Category;
import com.example.demo.services.ProductService;
import com.example.demo.services.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService cateService;

    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> listProduct = productService.getAllProducts();
        model.addAttribute("listProduct", listProduct);
        return "product/list";

    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Category> listCate = cateService.getAllCates();
        model.addAttribute("categories", listCate);
        model.addAttribute("product", new Product());
        return "product/add";
    }

    @PostMapping("/add")
    public String add(@Valid Product newProduct, BindingResult result, Model model,
            @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            List<Category> listCate = cateService.getAllCates();
            model.addAttribute("categories", listCate);
            return "product/add";
        }
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path filePath = Paths.get("src/main/resources/static/images", file.getOriginalFilename());
                Files.write(filePath, bytes);
                newProduct.setImage(file.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload the file: " + e.getMessage());
            }
        }
        productService.addProduct(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") int id, Model model) {
        List<Category> listCate = cateService.getAllCates();
        Optional<Product> editProduct = productService.getAllProducts().stream().filter(p -> p.getId() == id)
                .findFirst();
        if (editProduct.isPresent()) {
            model.addAttribute("categories", listCate);
            model.addAttribute("product", editProduct.get());
            return "product/edit";
        } else {
            return "not-found";
        }

    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute("product") @Valid Product updateProduct,
            BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            return "product/edit";
        }
        if (!file.isEmpty()) {
            updateProduct.setImage("images/" + file.getOriginalFilename());
        } else {
            updateProduct.setImage(null);
        }
        productService.updateProduct(updateProduct);

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";

    }

}

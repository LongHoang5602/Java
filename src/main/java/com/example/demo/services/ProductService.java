package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.IProductRepository;

@Service
public class ProductService {
    @Autowired
    private IProductRepository bookRepository;

    public List<Product> getAllProducts() {
        return bookRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> optional = bookRepository.findById(id);
        return optional.orElse(null);
    }

    public void addProduct(Product newProduct) {
        bookRepository.save(newProduct);
    }

    public void updateProduct(Product newProduct) {
        bookRepository.save(newProduct);
    }

    public void deleteProduct(Long id) {
        bookRepository.deleteById(id);
    }
}

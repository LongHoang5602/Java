package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.ICategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository cateRepository;

    public List<Category> getAllCates() {
        return cateRepository.findAll();
    }
}

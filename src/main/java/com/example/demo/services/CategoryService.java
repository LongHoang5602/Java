package com.example.demo.services;

import java.util.List;
import java.util.Optional;

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

    public Category getCateById(Long id) {
        Optional<Category> optional = cateRepository.findById(id);
        return optional.orElse(null);
    }

    public void addCate(Category newCate) {
        cateRepository.save(newCate);
    }

    public void updateCate(Category newCate) {
        cateRepository.save(newCate);
    }

    public void deleteCate(Long id) {
        cateRepository.deleteById(id);
    }
}

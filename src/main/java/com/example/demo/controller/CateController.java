package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Category;
import com.example.demo.services.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cates")
public class CateController {
    @Autowired
    private CategoryService cateService;

    @GetMapping
    public String showAllCate(Model model) {
        List<Category> listCate = cateService.getAllCates();
        model.addAttribute("listCate", listCate);
        return "cate/list";

    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("cate", new Category());
        return "cate/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("cate") @Valid Category newCate, BindingResult result, Model model) {
        if (result.hasErrors()) {

            // model.addAttribute("cate", new Category());
            return "cate/add";
        }
        cateService.addCate(newCate);
        return "redirect:/cates";
    }

    @GetMapping("/edit/{id}")
    public String editCateForm(@PathVariable("id") int id, Model model) {
        Optional<Category> editCate = cateService.getAllCates().stream().filter(p -> p.getId() == id).findFirst();
        if (editCate.isPresent()) {
            model.addAttribute("cate", editCate.get());
            return "cate/edit";
        } else {
            return "not-found";
        }

    }

    @PostMapping("/edit")
    public String editCate(@ModelAttribute("cate") @Valid Category updateCate,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cate/edit";
        }
        cateService.updateCate(updateCate);

        return "redirect:/cates";
    }

    @GetMapping("/delete/{id}")
    public String deleteCate(@PathVariable("id") Long id) {
        cateService.deleteCate(id);
        return "redirect:/cates";

    }

}

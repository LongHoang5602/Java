package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Book;
import com.example.demo.services.BookService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> listBook = bookService.getAllBooks();
        model.addAttribute("listBook", listBook);
        return "book/list";

    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/add")
    public String add(@Valid Book newBook, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", new Book());
            return "book/add";
        }
        // if (imageBook != null && imageBook.getSize() > 0) {
        // try {
        // File saveFile = new ClassPathResource("static/images").getFile();
        // String newImageFile = UUID.randomUUID() + ".png";
        // Path path = Paths.get(saveFile.getAbsolutePath() + File.separator +
        // newImageFile);
        // Files.copy(imageBook.getInputStream(), path,
        // StandardCopyOption.REPLACE_EXISTING);
        // newBook.setImage(newImageFile);
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // }
        // }

        bookService.addBook(newBook);
        return "redirect:/books";
    }
}

package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService cateService;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> listBook = bookService.getAllBooks();
        model.addAttribute("listBook", listBook);
        return "book/list";

    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Category> listCate = cateService.getAllCates();
        model.addAttribute("categories", listCate);
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/add")
    public String add(@Valid Book newBook, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> listCate = cateService.getAllCates();
            model.addAttribute("categories", listCate);
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

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") int id, Model model) {
        List<Category> listCate = cateService.getAllCates();
        Optional<Book> editBook = bookService.getAllBooks().stream().filter(p -> p.getId() == id).findFirst();
        if (editBook.isPresent()) {
            model.addAttribute("categories", listCate);
            model.addAttribute("book", editBook.get());
            return "book/edit";
        } else {
            return "not-found";
        }

    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") @Valid Book updateBook,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book/edit";
        }
        bookService.updateBook(updateBook);

        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";

    }

}

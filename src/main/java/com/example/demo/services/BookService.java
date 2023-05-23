package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.IBookRepository;

@Service
public class BookService {
    @Autowired
    private IBookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        return optional.orElse(null);
    }

    public void addBook(Book newBook) {
        bookRepository.save(newBook);
    }

    public void updateBook(Book newBook) {
        bookRepository.save(newBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

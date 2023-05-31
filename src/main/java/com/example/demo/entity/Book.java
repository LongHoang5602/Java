package com.example.demo.entity;

import com.example.validator.ValidCategoryId;
import com.example.validator.ValidUserId;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotEmpty(message = "Không được để trống")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Không được để trống")
    private String author;

    @Column(name = "image")
    @NotEmpty(message = "Không được để trống")
    private String image;

    @Column(name = "price")
    @NotNull(message = "Không được để trống")
    @Min(value = 1, message = "Giá sản phẩm không được bé hơn 1")
    @Max(value = 1000000, message = "Giá sản phẩm không được lớn hơn 1000000")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ValidCategoryId
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ValidUserId
    private User user;
}

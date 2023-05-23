package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Max(value = 1000, message = "Giá trị không hợp lệ")
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Không được để trống")
    private String title;

    @Column(name = "author")
    @NotBlank(message = "Không được để trống")
    private String author;

    @Column(name = "image")
    @NotBlank(message = "Không được để trống")
    private String image;

    @Column(name = "price")
    @NotNull(message = "Không được để trống")
    @Min(value = 1, message = "Giá sản phẩm không được bé hơn 1")
    @Max(value = 1000000, message = "Giá sản phẩm không được lớn hơn 1000000")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}

package com.example.demo.entity;

import com.example.demo.validator.annotation.ValidUserId;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Không được để trống")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "Không được để trống")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    @NotNull(message = "Không được để trống")
    @Min(value = 1, message = "Giá sản phẩm không được bé hơn 1")
    @Max(value = 1000000, message = "Giá sản phẩm không được lớn hơn 1000000")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;
}

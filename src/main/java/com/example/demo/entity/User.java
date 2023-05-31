package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.validator.ValidUsername;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Không được để trống")
    @Size(max = 50, message = "Không được lớn hơn 50 ký tự")
    @ValidUsername
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    @NotBlank(message = "Không được để trống")
    private String password;

    @Column(name = "email", length = 50)
    @Size(max = 50, message = "Không được lớn hơn 50 ký tự")
    @Email(message = "Không đúng định dạng")
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    @Size(max = 50, message = "Không được lớn hơn 50 ký tự")
    @NotBlank(message = "Không được để trống")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

}

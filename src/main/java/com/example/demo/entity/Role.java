package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    @NotEmpty(message = "Không được để trống")
    @Size(max = 50, message = "Nhập dưới 50 ký tự")
    private String name;

    @Column(name = "desc")
    @Size(max = 250, message = "Nhập dưới 250 ký tự")
    private String desc;

    @Column(name = "image")
    @NotEmpty(message = "Không được để trống")
    private String image;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}

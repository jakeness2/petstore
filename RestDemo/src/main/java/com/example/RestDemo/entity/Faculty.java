package com.example.RestDemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String department;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Course> courses;
}

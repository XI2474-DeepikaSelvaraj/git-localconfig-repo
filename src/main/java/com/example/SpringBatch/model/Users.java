package com.example.SpringBatch.model;


import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String department;
    private Integer salary;
    private Integer age;
}

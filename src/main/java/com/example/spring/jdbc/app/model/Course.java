package com.example.spring.jdbc.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Course {

private int id;
private String name;
private String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

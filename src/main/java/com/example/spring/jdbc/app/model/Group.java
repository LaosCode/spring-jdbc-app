package com.example.spring.jdbc.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private int id;
    private String name;

    public Group(String name) {
        this.name = name;
    }
}

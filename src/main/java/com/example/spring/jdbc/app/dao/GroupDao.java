package com.example.spring.jdbc.app.dao;

import com.example.spring.jdbc.app.model.Group;

import java.util.List;

public interface GroupDao {

    void add(Group group);

    List<Group> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents);
}

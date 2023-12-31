package com.example.spring.jdbc.app.dao;

import com.example.spring.jdbc.app.model.Group;

import java.util.List;

public interface GroupDao {

    void add(String groupName);

    List<Group> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents);
}

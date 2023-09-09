package com.example.springJDBCApp.dao;

import com.example.springJDBCApp.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupDao {

    void add(String groupName);

    List<Group> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents);
}

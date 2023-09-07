package com.example.SpringJDBCApp.dao;

import com.example.SpringJDBCApp.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupDao {

    void addGroup(String groupName);

    Optional<List<Group>> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents);
}

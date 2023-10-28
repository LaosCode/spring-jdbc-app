package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.GroupDao;
import com.example.spring.jdbc.app.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupDao groupDao;

    public List<Group> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents) {
        return groupDao.findAllHaveCertainAmountOfStudents(minimumNumberOfStudents);
    }

    public void add(Group group) {
        groupDao.add(group);
    }
}

package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.GroupDao;
import com.example.spring.jdbc.app.dao.repository.GroupRepository;
import com.example.spring.jdbc.app.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents) {
        return groupRepository.findAllHaveCertainAmountOfStudents(minimumNumberOfStudents);
    }

    public void add(Group group) {
        groupRepository.save(group);
    }
}

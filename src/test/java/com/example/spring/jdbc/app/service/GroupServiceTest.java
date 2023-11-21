package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.CourseDao;
import com.example.spring.jdbc.app.dao.GroupDao;
import com.example.spring.jdbc.app.dao.repository.GroupRepository;
import com.example.spring.jdbc.app.model.Group;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {GroupService.class})
class GroupServiceTest {

    @MockBean
    GroupRepository groupRepository;
    @Autowired
    GroupService underTestService;

    @Test
    public void shouldFindAllHaveCertainAmountOfStudents() {
        int minNumberOfStudents = 5;

        underTestService.findAllHaveCertainAmountOfStudents(minNumberOfStudents);

        verify(groupRepository).findAllHaveCertainAmountOfStudents(minNumberOfStudents);
    }

    @Test
    public void shouldAddGroup() {
        Group group = new Group("Group1");
        group.setId(1);

        underTestService.add(group);

        verify(groupRepository).save(group);
    }
}
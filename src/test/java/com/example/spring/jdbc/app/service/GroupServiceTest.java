package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.CourseDao;
import com.example.spring.jdbc.app.dao.GroupDao;
import com.example.spring.jdbc.app.model.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {GroupService.class})
class GroupServiceTest {

    @MockBean
    GroupDao groupDao;

    @Autowired
    GroupService underTestService;

    @Test
    public void shouldFindAllHaveCertainAmountOfStudents() {
        int minNumberOfStudents = 5;

        underTestService.findAllHaveCertainAmountOfStudents(minNumberOfStudents);

        verify(groupDao).findAllHaveCertainAmountOfStudents(minNumberOfStudents);
    }

    @Test
    public void shouldAddGroup() {
        Group group = new Group("Group1");
        group.setId(1);

        underTestService.add(group);

        verify(groupDao).add(group);
    }
}
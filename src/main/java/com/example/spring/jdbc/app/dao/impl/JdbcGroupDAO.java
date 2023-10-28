package com.example.spring.jdbc.app.dao.impl;

import com.example.spring.jdbc.app.model.Group;
import com.example.spring.jdbc.app.dao.GroupDao;
import com.example.spring.jdbc.app.dao.mappers.GroupRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class JdbcGroupDAO implements GroupDao {
    private final JdbcTemplate jdbcTemplate;

    public void add(Group group) {
        jdbcTemplate.update("INSERT INTO groups (group_name) VALUES (?)", group.getName());
    }

    public List<Group> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents) {
        return jdbcTemplate.query("select g.group_id,group_name, count(*) as n_students" +
                " from students join groups g on students.group_id = g.group_id" +
                " group by g.group_id" +
                " having count(group_name) >= ?", new GroupRowMapper(), minimumNumberOfStudents);
    }
}

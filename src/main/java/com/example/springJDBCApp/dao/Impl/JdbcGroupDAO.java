package com.example.springJDBCApp.dao.Impl;

import com.example.springJDBCApp.dao.GroupDao;
import com.example.springJDBCApp.dao.mappers.GroupRowMapper;
import com.example.springJDBCApp.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class JdbcGroupDAO implements GroupDao {
    private final JdbcTemplate jdbcTemplate;

    public void add(String groupName) {
        jdbcTemplate.update("INSERT INTO groups (group_name) VALUES (?)", groupName);
    }

    public List<Group> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents) {
        return jdbcTemplate.query("select g.group_id,group_name, count(*) as n_students" +
                " from students join groups g on students.group_id = g.group_id" +
                " group by g.group_id" +
                " having count(group_name) >= ?", new GroupRowMapper(), minimumNumberOfStudents);
    }
}

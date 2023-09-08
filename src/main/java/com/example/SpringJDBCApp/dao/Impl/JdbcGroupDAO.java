package com.example.SpringJDBCApp.dao.Impl;

import com.example.SpringJDBCApp.dao.CourseDao;
import com.example.SpringJDBCApp.dao.GroupDao;
import com.example.SpringJDBCApp.dao.mappers.GroupRowMapper;
import com.example.SpringJDBCApp.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class JdbcGroupDAO implements GroupDao {
    private final JdbcTemplate jdbcTemplate;

    public void addGroup(String groupName) {
        jdbcTemplate.update("INSERT INTO groups (group_name) VALUES (?)",groupName);
    }

    public Optional<List<Group>> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents) {
      return Optional.of(jdbcTemplate.query("select g.group_id,group_name, count(*) as n_students" +
               " from students join groups g on students.group_id = g.group_id" +
               " group by g.group_id" +
               " having count(group_name) >= ?",new GroupRowMapper(),minimumNumberOfStudents));
    }
}

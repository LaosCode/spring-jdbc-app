package com.example.SpringJDBCApp.dao.mappers;

import com.example.SpringJDBCApp.model.Course;
import com.example.SpringJDBCApp.model.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class GroupRowMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();
        group.setId(rs.getInt(1));
        group.setName(rs.getString(1));
        return group;
    }
}

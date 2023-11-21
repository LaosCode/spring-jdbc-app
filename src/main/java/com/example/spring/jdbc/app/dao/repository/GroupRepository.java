package com.example.spring.jdbc.app.dao.repository;

import com.example.spring.jdbc.app.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    @Query("select s.group from Student s join s.group g group by s.group.id having count (g.id) <?1")
    List<Group> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents);
}

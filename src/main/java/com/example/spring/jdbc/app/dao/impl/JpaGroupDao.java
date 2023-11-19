package com.example.spring.jdbc.app.dao.impl;

import com.example.spring.jdbc.app.dao.GroupDao;
import com.example.spring.jdbc.app.model.Group;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class JpaGroupDao implements GroupDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void add(Group group) {
        entityManager.persist(group);
    }

    @Override
    public List<Group> findAllHaveCertainAmountOfStudents(int minimumNumberOfStudents) {
        return entityManager
                .createQuery("select s.group from Student s join s.group g group by s.group.id having count (g.id) < :param", Group.class)
                .setParameter("param", minimumNumberOfStudents)
                .getResultList();
    }
}

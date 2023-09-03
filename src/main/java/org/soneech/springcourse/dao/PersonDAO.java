package org.soneech.springcourse.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.soneech.springcourse.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// example of N + 1 problem
@Component
public class PersonDAO {
    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testProblem() {
        Session session = entityManager.unwrap(Session.class);

        // 1 query
        List<Person> people =
                session.createQuery("select p from Person p", Person.class).getResultList();

        // N queries
        for (Person person: people) {
            System.out.println("Items for " + person.getName() + ":");
            person.getItems().forEach(System.out::println);
            System.out.println();
        }
    }

    @Transactional(readOnly = true)
    public void problemSolution() {  // using left join
        Session session = entityManager.unwrap(Session.class);
        // 1 query
        List<Person> people = session
                .createQuery("select p from Person p LEFT JOIN FETCH p.items", Person.class)
                .getResultList();

        // 0 queries
        for (Person person: people) {
            System.out.println("Items for " + person.getName() + ": " + person.getItems());
            System.out.println();
        }
    }
}

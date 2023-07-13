package org.soneech.springcourse.dao;

import org.soneech.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String email) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[] {email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (name, age, email, address) VALUES(?, ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail(), person.getAddress());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=?, address=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(),
                updatedPerson.getEmail(), updatedPerson.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }


    // Testing the performance of batch update

    public void testMultipleUpdate() {
        List<Person> people = createPeople("Ordinary");
        long start = System.currentTimeMillis();

        for (Person person: people) {
            save(person);
        }

        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
    }

    public void testBatchUpdate() {
        List<Person> people = createPeople("Batch");
        long start = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person (name, age, email) VALUES(?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, people.get(i).getName());
                        ps.setInt(2, people.get(i).getAge());
                        ps.setString(3, people.get(i).getEmail());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });

        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
    }

    public List<Person> createPeople(String namePattern) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i ++) {
            Person person = new Person();
            person.setName(namePattern + i);
            person.setAge(20);
            person.setEmail("test" + i + "@yandex.ru");
            person.setAddress("some address...");

            people.add(person);
        }

        return people;
    }
}

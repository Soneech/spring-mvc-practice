package org.soneech.springcourse.repository;

import org.soneech.springcourse.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmail(String email);
    List<Person> findByName(String name);
    List<Person> findByNameOrderByAge(String name);
    List<Person> findByNameStartingWith(String start);
}

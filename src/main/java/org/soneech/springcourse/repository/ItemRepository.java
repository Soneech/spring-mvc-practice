package org.soneech.springcourse.repository;

import org.soneech.springcourse.model.Item;
import org.soneech.springcourse.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByOwner(Person owner);
    Item findByName(String name);
}

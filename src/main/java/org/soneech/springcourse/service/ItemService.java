package org.soneech.springcourse.service;

import org.soneech.springcourse.model.Item;
import org.soneech.springcourse.model.Person;
import org.soneech.springcourse.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findByOwner(Person owner) {
        return itemRepository.findByOwner(owner);
    }

    public Item findByName(String name) {
        return itemRepository.findByName(name);
    }
}

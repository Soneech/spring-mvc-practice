package org.soneech.springcourse.controller;

import org.soneech.springcourse.service.ItemService;
import org.soneech.springcourse.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final PersonService personService;
    private final ItemService itemService;

    @Autowired
    public MainController(PersonService personService, ItemService itemService) {
        this.personService = personService;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String homePage() {
        itemService.findByName("TV");
        itemService.findByOwner(personService.findById(3));

        personService.test();
        return "index";
    }
}

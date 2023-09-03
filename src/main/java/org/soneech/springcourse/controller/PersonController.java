package org.soneech.springcourse.controller;

import jakarta.validation.Valid;
import org.soneech.springcourse.dao.PersonDAO;
import org.soneech.springcourse.model.Person;
import org.soneech.springcourse.repository.ItemRepository;
import org.soneech.springcourse.service.PersonService;
import org.soneech.springcourse.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final ItemRepository itemRepository;
    private final PersonValidator personValidator;
    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonService personService, ItemRepository itemRepository,
                            PersonValidator personValidator, PersonDAO personDAO) {
        this.personService = personService;
        this.itemRepository = itemRepository;
        this.personValidator = personValidator;
        this.personDAO = personDAO;
    }

    @GetMapping("test-problem")
    public String testProblem() {
        personDAO.testProblem();
        return "redirect:/people";
    }

    @GetMapping("problem-solution")
    public String problemSolution() {
        personDAO.problemSolution();
        return "redirect:/people";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("people", personService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")  // use id in url
    public String showPerson(@PathVariable("id") int id, Model model) {
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        model.addAttribute("items", itemRepository.findByOwner(person));
        return "people/show";
    }

    @GetMapping("/new")
    public String createPage(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String updatePage(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/edit";
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}

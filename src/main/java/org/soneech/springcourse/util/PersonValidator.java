package org.soneech.springcourse.util;

import org.soneech.springcourse.model.Person;
import org.soneech.springcourse.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    // checking user's email
    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> foundPerson = personService.findByEmail(person.getEmail());

        if (foundPerson.isPresent() && foundPerson.get().getId() != person.getId()) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}

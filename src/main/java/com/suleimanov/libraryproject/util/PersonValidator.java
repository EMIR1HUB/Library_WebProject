package com.suleimanov.libraryproject.util;

import com.suleimanov.libraryproject.dao.PersonDAO;
import com.suleimanov.libraryproject.models.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonInfo.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PersonInfo person = (PersonInfo) obj;

        // see if there is a person with the same email
        if (personDAO.show(person.getId(), person.getEmail()).isPresent())
            errors.rejectValue("email", "", "Такой email уже используется");
    }
}

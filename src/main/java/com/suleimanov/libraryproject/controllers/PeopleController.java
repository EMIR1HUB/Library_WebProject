package com.suleimanov.libraryproject.controllers;

import com.suleimanov.libraryproject.dao.BookDAO;
import com.suleimanov.libraryproject.dao.PersonDAO;
import com.suleimanov.libraryproject.models.PersonInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", bookDAO.index(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") PersonInfo personInfo) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid PersonInfo personInfo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "people/new";
        personDAO.save(personInfo);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid PersonInfo personInfo,
                         BindingResult bindingResult, @PathVariable("id") Long id) {

        if (bindingResult.hasErrors()) return "people/edit";
        personDAO.update(id, personInfo);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}

package com.suleimanov.libraryproject.controllers;

import com.suleimanov.libraryproject.dao.BookDAO;
import com.suleimanov.libraryproject.dao.PersonDAO;
import com.suleimanov.libraryproject.models.BookInfo;
import com.suleimanov.libraryproject.models.PersonInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model,
                       @ModelAttribute("person") PersonInfo person){
        model.addAttribute("book", bookDAO.show(id));

        Optional<PersonInfo> bookOwner = bookDAO.getBookOwner(id);

        if(bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") BookInfo bookInfo) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid BookInfo bookInfo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "books/new";
        bookDAO.save(bookInfo);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid BookInfo bookInfo,
                         BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) return "books/edit";

        bookDAO.update(id, bookInfo);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String release(@PathVariable("id") Long id, @ModelAttribute("person") PersonInfo personInfo){
        bookDAO.assign(id, personInfo);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release (@PathVariable("id") Long id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }
}

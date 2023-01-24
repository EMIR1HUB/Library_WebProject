package com.suleimanov.libraryproject.dao;

import com.suleimanov.libraryproject.models.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PersonInfo> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(PersonInfo.class));
    }

    public PersonInfo show(Long id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(PersonInfo.class)).stream().findAny().orElse(null);
    }

    public void save(PersonInfo personInfo) {
        String SQL = "INSERT INTO person(last_name, name, patronymic, date_birth) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(SQL, personInfo.getLastName(), personInfo.getName(), personInfo.getPatronymic(), personInfo.getDateBirth());
    }

    public void update(Long id, PersonInfo updatePerson) {
        String SQL = "UPDATE person SET last_name = ?, name = ?, patronymic = ?, date_birth = ? WHERE id = ?";
        jdbcTemplate.update(SQL, updatePerson.getLastName(), updatePerson.getName(), updatePerson.getPatronymic(), updatePerson.getDateBirth(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }


}

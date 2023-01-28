package com.suleimanov.libraryproject.dao;

import com.suleimanov.libraryproject.models.BookInfo;
import com.suleimanov.libraryproject.models.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


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

    public Optional<PersonInfo> show(Long id, String email) {
        return jdbcTemplate.query("SELECT * FROM person WHERE email = ? AND id != " + id, new Object[]{email},
                new BeanPropertyRowMapper<>(PersonInfo.class)).stream().findAny();
    }

    public void save(PersonInfo personInfo) {
        String SQL = "INSERT INTO person(last_name, name, patronymic, date_birth, email, phone) VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(SQL, personInfo.getLastName(), personInfo.getName(), personInfo.getPatronymic(),
                personInfo.getDateBirth(), personInfo.getEmail(), personInfo.getPhone());
    }

    public void update(Long id, PersonInfo updatePerson) {
        String SQL = "UPDATE person SET last_name = ?, name = ?, patronymic = ?, date_birth = ?, email = ?, phone = ? WHERE id = ?;";
        jdbcTemplate.update(SQL, updatePerson.getLastName(), updatePerson.getName(), updatePerson.getPatronymic(),
                updatePerson.getDateBirth(), updatePerson.getEmail(), updatePerson.getPhone(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }


}

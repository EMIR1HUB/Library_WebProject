package com.suleimanov.libraryproject.dao;

import com.suleimanov.libraryproject.models.PersonPhotoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonPhotoDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonPhotoDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<PersonPhotoInfo> showPath(Long id){
        String SQL = "SELECT path_to_the_photo FROM person_photo WHERE person_id = ?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(PersonPhotoInfo.class)).stream().findAny();
    }
}

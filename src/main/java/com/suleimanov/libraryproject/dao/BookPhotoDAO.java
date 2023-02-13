package com.suleimanov.libraryproject.dao;

import com.suleimanov.libraryproject.models.BookInfo;
import com.suleimanov.libraryproject.models.BookPhotoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookPhotoDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookPhotoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BookPhotoInfo> index() {
        return jdbcTemplate.query("SELECT * FROM book_photo", new BeanPropertyRowMapper<>(BookPhotoInfo.class));
    }
}

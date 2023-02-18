package com.suleimanov.libraryproject.dao;

import com.suleimanov.libraryproject.models.BookInfo;
import com.suleimanov.libraryproject.models.BookPhotoInfo;
import com.suleimanov.libraryproject.models.PersonPhotoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookPhotoDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookPhotoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<BookPhotoInfo> showPath(Long id){
        String SQL = "SELECT path_file_name FROM book_photo WHERE book_id = ?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(BookPhotoInfo.class)).stream().findAny();
    }

    public List<BookPhotoInfo> index() {
        return jdbcTemplate.query("SELECT * FROM book_photo", new BeanPropertyRowMapper<>(BookPhotoInfo.class));
    }
}

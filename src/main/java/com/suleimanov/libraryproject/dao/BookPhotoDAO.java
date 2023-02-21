package com.suleimanov.libraryproject.dao;

import com.suleimanov.libraryproject.models.BookInfo;
import com.suleimanov.libraryproject.models.BookPhotoInfo;
import com.suleimanov.libraryproject.models.PersonInfo;
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

    public void save(Long id, String fileName){
        String SQL = "INSERT INTO book_photo(book_id, path_file_name) VALUES (?, ?)";
        jdbcTemplate.update(SQL, id, fileName);
    }

    public void save(String fileName){
        String SQL = "INSERT INTO book_photo(book_id, path_file_name) VALUES (?, ?)";
        jdbcTemplate.update(SQL, getLastId().getId(), fileName);
    }

    public PersonInfo getLastId(){
        return jdbcTemplate.query("SELECT id FROM book ORDER BY id DESC LIMIT 1;",
                new BeanPropertyRowMapper<>(PersonInfo.class)).stream().findAny().orElse(null);
    }

    public Optional<BookPhotoInfo> showPath(Long id){
        String SQL = "SELECT path_file_name FROM book_photo WHERE book_id = ?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(BookPhotoInfo.class)).stream().findAny();
    }

    public List<BookPhotoInfo> index() {
        return jdbcTemplate.query("SELECT * FROM book_photo", new BeanPropertyRowMapper<>(BookPhotoInfo.class));
    }

    public void update(Long id, String fileName){
        String SQL = "UPDATE book_photo SET path_file_name = ? WHERE book_id = ?";
        jdbcTemplate.update(SQL, fileName, id);
    }
}

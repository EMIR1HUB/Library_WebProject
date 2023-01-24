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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BookInfo> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(BookInfo.class));
    }

    public List<BookInfo> index(Long id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(BookInfo.class));
    }

    public BookInfo show(Long id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(BookInfo.class)).stream().findAny().orElse(null);
    }

    public Optional<PersonInfo> getBookOwner(Long id){
        String SQL = "SELECT p.id, p.last_name, p.name, p.patronymic FROM book\n" +
                "    JOIN person p on p.id = book.person_id\n" +
                "    WHERE book.id = ?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(PersonInfo.class)).stream().findAny();
    }

    public void save(BookInfo bookInfo) {
        String SQL = "INSERT INTO book(name, author, year) VALUES (?, ?, ?);";
        jdbcTemplate.update(SQL, bookInfo.getName(), bookInfo.getAuthor(), bookInfo.getYear());
    }

    public void update(Long id, BookInfo updateBook) {
        String SQL = "UPDATE book SET name = ?, author = ?, year = ? WHERE id = ?";
        jdbcTemplate.update(SQL, updateBook.getName(), updateBook.getAuthor(), updateBook.getYear(), id);
    }

    public void assign(Long id, PersonInfo person){
        String SQL = "UPDATE book SET person_id = ? WHERE id = ?";
        jdbcTemplate.update(SQL, person.getId(), id);
    }

    public void release(Long id){
        String SQL = "UPDATE book SET person_id = null WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

}

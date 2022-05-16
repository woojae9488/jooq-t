package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql("classpath:/sql/jooq_test_init.sql")
class BookRepositoryTests {

    @Autowired
    private BookRepository repository;

    @Test
    void selectById_test() {
        Book book = repository.selectById(1);

        assertAll(
                () -> assertEquals(1, book.getId()),
                () -> assertEquals(1, book.getAuthorId()),
                () -> assertEquals("bk_t_1", book.getTitle()),
                () -> assertEquals(3000, book.getPrice())
        );
    }

    @Test
    void selectAll_test() {
        List<Book> books = repository.selectAll();

        assertEquals(32, books.size());

        Book firstBook = books.get(0);

        assertAll(
                () -> assertEquals(1, firstBook.getId()),
                () -> assertEquals(1, firstBook.getAuthorId()),
                () -> assertEquals("bk_t_1", firstBook.getTitle()),
                () -> assertEquals(3000, firstBook.getPrice())
        );
    }

}
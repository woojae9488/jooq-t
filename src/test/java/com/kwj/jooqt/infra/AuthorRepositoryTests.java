package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql("classpath:/sql/jooq_test_init.sql")
class AuthorRepositoryTests {

    @Autowired
    private AuthorRepository repository;

    @Test
    void selectById_test() {
        Author author = repository.selectById(1);

        assertAll(
                () -> assertEquals(1, author.getId()),
                () -> assertEquals(1, author.getPublisherId()),
                () -> assertEquals("aut_1", author.getName()),
                () -> assertEquals("20000101", author.getBirthday()),
                () -> assertEquals('M', author.getGender())
        );
    }

    @Test
    void selectAll_test() {
        List<Author> authors = repository.selectAll();

        assertEquals(12, authors.size());

        Author firstAuthor = authors.get(0);

        assertAll(
                () -> assertEquals(1, firstAuthor.getId()),
                () -> assertEquals(1, firstAuthor.getPublisherId()),
                () -> assertEquals("aut_1", firstAuthor.getName()),
                () -> assertEquals("20000101", firstAuthor.getBirthday()),
                () -> assertEquals('M', firstAuthor.getGender())
        );
    }

}
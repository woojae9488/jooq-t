package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Author;
import com.kwj.jooqt.domain.Book;
import com.kwj.jooqt.domain.value.AuthorBooksKey;
import com.kwj.jooqt.util.JooqRecordHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql("classpath:sql/jooq_test_init.sql")
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
                () -> assertEquals("2000-01-01", author.getBirthday()),
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
                () -> assertEquals("2000-01-01", firstAuthor.getBirthday()),
                () -> assertEquals('M', firstAuthor.getGender())
        );
    }

    @Test
    void selectAuthorBooksEntryById_test() {
        Map.Entry<AuthorBooksKey, List<Book>> authorBooksEntry = repository.selectAuthorBooksEntryById(1);

        AuthorBooksKey key = authorBooksEntry.getKey();
        List<Book> books = authorBooksEntry.getValue();

        assertAll(
                () -> assertEquals(1, key.getId()),
                () -> assertEquals("aut_1", key.getName()),
                () -> assertEquals(3, books.size())
        );

        Book book = books.get(0);

        assertAll(
                () -> assertEquals(1, book.getId()),
                () -> assertEquals(1, book.getAuthorId()),
                () -> assertEquals("bk_t_1", book.getTitle()),
                () -> assertEquals(3000, book.getPrice())
        );
    }

    @Test
    void selectAllAuthorBooksMap_test() {
        Map<AuthorBooksKey, List<Book>> authorBooksMap = repository.selectAllAuthorBooksMap();

        assertEquals(12, authorBooksMap.size());

        Map.Entry<AuthorBooksKey, List<Book>> authorBooksEntry = JooqRecordHelper.firstEntry(authorBooksMap);

        AuthorBooksKey key = authorBooksEntry.getKey();
        List<Book> books = authorBooksEntry.getValue();

        assertAll(
                () -> assertEquals(1, key.getId()),
                () -> assertEquals("aut_1", key.getName()),
                () -> assertEquals(3, books.size())
        );

        Book book = books.get(0);

        assertAll(
                () -> assertEquals(1, book.getId()),
                () -> assertEquals(1, book.getAuthorId()),
                () -> assertEquals("bk_t_1", book.getTitle()),
                () -> assertEquals(3000, book.getPrice())
        );
    }

}
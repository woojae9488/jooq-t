package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Book;
import com.kwj.jooqt.domain.Publisher;
import com.kwj.jooqt.domain.value.AuthorBooksKey;
import com.kwj.jooqt.domain.value.PublisherPerformanceKey;
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
class PublisherRepositoryTests {

    @Autowired
    private PublisherRepository repository;

    @Test
    void selectById_test() {
        Publisher publisher = repository.selectById(1);

        assertAll(
                () -> assertEquals(1, publisher.getId()),
                () -> assertEquals("pub_a", publisher.getName()),
                () -> assertEquals("pub_addr_a", publisher.getAddress())
        );
    }

    @Test
    void selectAll_test() {
        List<Publisher> publishers = repository.selectAll();

        assertEquals(4, publishers.size());

        Publisher firstPublisher = publishers.get(0);

        assertAll(
                () -> assertEquals(1, firstPublisher.getId()),
                () -> assertEquals("pub_a", firstPublisher.getName()),
                () -> assertEquals("pub_addr_a", firstPublisher.getAddress())
        );
    }

    @Test
    void selectPublisherPerformanceEntryById_test() {
        Map.Entry<PublisherPerformanceKey, Map<AuthorBooksKey, List<Book>>> publisherPerformanceEntry =
                repository.selectPublisherPerformanceEntryById(1);

        PublisherPerformanceKey key = publisherPerformanceEntry.getKey();
        Map<AuthorBooksKey, List<Book>> authorBooksMap = publisherPerformanceEntry.getValue();

        assertAll(
                () -> assertEquals(1, key.getId()),
                () -> assertEquals("pub_a", key.getName()),
                () -> assertEquals("pub_addr_a", key.getAddress()),
                () -> assertEquals(2, authorBooksMap.size())
        );

        Map.Entry<AuthorBooksKey, List<Book>> authorBooksEntry = JooqRecordHelper.firstEntry(authorBooksMap);

        AuthorBooksKey authorKey = authorBooksEntry.getKey();
        List<Book> books = authorBooksEntry.getValue();

        assertAll(
                () -> assertEquals(1, authorKey.getId()),
                () -> assertEquals("aut_1", authorKey.getName()),
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
    void selectPublisherPerformanceMap_test() {
        Map<PublisherPerformanceKey, Map<AuthorBooksKey, List<Book>>> publisherPerformanceMap =
                repository.selectPublisherPerformanceMap();

        assertEquals(4, publisherPerformanceMap.size());

        Map.Entry<PublisherPerformanceKey, Map<AuthorBooksKey, List<Book>>> publisherPerformanceEntry =
                JooqRecordHelper.firstEntry(publisherPerformanceMap);

        PublisherPerformanceKey key = publisherPerformanceEntry.getKey();
        Map<AuthorBooksKey, List<Book>> authorBooksMap = publisherPerformanceEntry.getValue();

        assertAll(
                () -> assertEquals(1, key.getId()),
                () -> assertEquals("pub_a", key.getName()),
                () -> assertEquals("pub_addr_a", key.getAddress()),
                () -> assertEquals(2, authorBooksMap.size())
        );

        Map.Entry<AuthorBooksKey, List<Book>> authorBooksEntry = JooqRecordHelper.firstEntry(authorBooksMap);

        AuthorBooksKey authorKey = authorBooksEntry.getKey();
        List<Book> books = authorBooksEntry.getValue();

        assertAll(
                () -> assertEquals(1, authorKey.getId()),
                () -> assertEquals("aut_1", authorKey.getName()),
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
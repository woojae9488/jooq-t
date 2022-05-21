package com.kwj.jooqt.infra.collector;

import com.kwj.jooqt.domain.Book;
import com.kwj.jooqt.domain.value.AuthorBooksKey;
import com.kwj.jooqt.domain.value.PublisherPerformanceKey;
import org.jooq.Record;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.kwj.jooqt.domain.generated.public_.tables.Author.AUTHOR;
import static com.kwj.jooqt.domain.generated.public_.tables.Book.BOOK;
import static com.kwj.jooqt.domain.generated.public_.tables.Publisher.PUBLISHER;

public class PublisherCollectors {

    public static Collector<? super Record, ?, Map<PublisherPerformanceKey, Map<AuthorBooksKey, List<Book>>>>
            publisherPerformanceMapCollector = Collectors.groupingBy(
            r -> new PublisherPerformanceKey(
                    r.getValue(PUBLISHER.ID),
                    r.getValue(PUBLISHER.NAME),
                    r.getValue(PUBLISHER.ADDRESS)
            ),
            LinkedHashMap::new,
            Collectors.groupingBy(
                    r -> new AuthorBooksKey(
                            r.getValue(AUTHOR.ID),
                            r.getValue(AUTHOR.NAME)
                    ),
                    LinkedHashMap::new,
                    Collectors.mapping(
                            r -> new Book(
                                    r.getValue(BOOK.ID),
                                    r.getValue(BOOK.AUTHOR_ID),
                                    r.getValue(BOOK.TITLE),
                                    r.getValue(BOOK.PRICE)
                            ),
                            Collectors.toList()
                    )
            ));

}

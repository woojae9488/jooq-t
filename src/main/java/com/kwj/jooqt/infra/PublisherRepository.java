package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Book;
import com.kwj.jooqt.domain.Publisher;
import com.kwj.jooqt.domain.value.AuthorBooksKey;
import com.kwj.jooqt.domain.value.PublisherPerformanceKey;
import com.kwj.jooqt.infra.binder.PublisherBinders;
import com.kwj.jooqt.util.JooqRecordHelper;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.kwj.jooqt.domain.generated.public_.tables.Author.AUTHOR;
import static com.kwj.jooqt.domain.generated.public_.tables.Book.BOOK;
import static com.kwj.jooqt.domain.generated.public_.tables.Publisher.PUBLISHER;

@Repository
@RequiredArgsConstructor
public class PublisherRepository {

    private final DSLContext dslContext;

    public Publisher selectById(int id) {
        return dslContext.select()
                .from(PUBLISHER)
                .where(PUBLISHER.ID.eq(id))
                .fetchOneInto(Publisher.class);
    }

    public List<Publisher> selectAll() {
        return dslContext.select()
                .from(PUBLISHER)
                .fetchInto(Publisher.class);
    }

    public Map.Entry<PublisherPerformanceKey, Map<AuthorBooksKey, List<Book>>>
    selectPublisherPerformanceEntryById(int id) {
        return JooqRecordHelper.firstEntry(
                dslContext.select(PUBLISHER.ID, PUBLISHER.NAME, PUBLISHER.ADDRESS)
                        .select(AUTHOR.ID, AUTHOR.NAME)
                        .select(BOOK.fields())
                        .from(PUBLISHER)
                        .join(AUTHOR).on(PUBLISHER.ID.eq(AUTHOR.PUBLISHER_ID))
                        .join(BOOK).on(AUTHOR.ID.eq(BOOK.AUTHOR_ID))
                        .where(PUBLISHER.ID.eq(id))
                        .collect(PublisherBinders.publisherPerformanceMapCollector),
                id
        );
    }

    public Map<PublisherPerformanceKey, Map<AuthorBooksKey, List<Book>>> selectPublisherPerformanceMap() {
        return dslContext.select(PUBLISHER.ID, PUBLISHER.NAME, PUBLISHER.ADDRESS)
                .select(AUTHOR.ID, AUTHOR.NAME)
                .select(BOOK.fields())
                .from(PUBLISHER)
                .join(AUTHOR).on(PUBLISHER.ID.eq(AUTHOR.PUBLISHER_ID))
                .join(BOOK).on(AUTHOR.ID.eq(BOOK.AUTHOR_ID))
                .collect(PublisherBinders.publisherPerformanceMapCollector);
    }

}

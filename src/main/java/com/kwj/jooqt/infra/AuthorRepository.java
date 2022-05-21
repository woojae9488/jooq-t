package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Author;
import com.kwj.jooqt.domain.Book;
import com.kwj.jooqt.domain.value.AuthorBooksKey;
import com.kwj.jooqt.infra.collector.AuthorCollectors;
import com.kwj.jooqt.util.JooqRecordHelper;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.kwj.jooqt.domain.generated.public_.tables.Author.AUTHOR;
import static com.kwj.jooqt.domain.generated.public_.tables.Book.BOOK;

@Repository
@RequiredArgsConstructor
public class AuthorRepository {

    private final DSLContext dslContext;

    public Author selectById(int id) {
        return dslContext.select()
                .from(AUTHOR)
                .where(AUTHOR.ID.eq(id))
                .fetchOneInto(Author.class);
    }

    public List<Author> selectAll() {
        return dslContext.select()
                .from(AUTHOR)
                .fetchInto(Author.class);
    }

    public Map.Entry<AuthorBooksKey, List<Book>> selectAuthorBooksEntryById(int id) {
        return JooqRecordHelper.firstEntry(
                dslContext.select(AUTHOR.ID, AUTHOR.NAME)
                        .select(BOOK.fields())
                        .from(AUTHOR)
                        .join(BOOK).on(AUTHOR.ID.eq(BOOK.AUTHOR_ID))
                        .where(AUTHOR.ID.eq(id))
                        .stream()
                        .collect(AuthorCollectors.authorBooksMapCollector),
                id
        );
    }

    public Map<AuthorBooksKey, List<Book>> selectAllAuthorBooksMap() {
        return dslContext.select(AUTHOR.ID, AUTHOR.NAME)
                .select(BOOK.fields())
                .from(AUTHOR)
                .join(BOOK).on(AUTHOR.ID.eq(BOOK.AUTHOR_ID))
                .stream()
                .collect(AuthorCollectors.authorBooksMapCollector);
    }

}

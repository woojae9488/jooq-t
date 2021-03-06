package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Author;
import com.kwj.jooqt.domain.Book;
import com.kwj.jooqt.domain.value.AuthorBooksKey;
import com.kwj.jooqt.infra.binder.AuthorBinders;
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
                .fetchOne(AuthorBinders.authorRecordMapper);
    }

    public List<Author> selectAll() {
        return dslContext.select()
                .from(AUTHOR)
                .fetch(AuthorBinders.authorRecordMapper);
    }

    public Map.Entry<AuthorBooksKey, List<Book>> selectAuthorBooksEntryById(int id) {
        return JooqRecordHelper.firstEntry(
                dslContext.select(AUTHOR.ID, AUTHOR.NAME)
                        .select(BOOK.fields())
                        .from(AUTHOR)
                        .join(BOOK).on(AUTHOR.ID.eq(BOOK.AUTHOR_ID))
                        .where(AUTHOR.ID.eq(id))
                        .collect(AuthorBinders.authorBooksMapCollector),
                id
        );
    }

    public Map<AuthorBooksKey, List<Book>> selectAllAuthorBooksMap() {
        return dslContext.select(AUTHOR.ID, AUTHOR.NAME)
                .select(BOOK.fields())
                .from(AUTHOR)
                .join(BOOK).on(AUTHOR.ID.eq(BOOK.AUTHOR_ID))
                .collect(AuthorBinders.authorBooksMapCollector);
    }

}

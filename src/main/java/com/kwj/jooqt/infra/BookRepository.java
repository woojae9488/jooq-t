package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Book;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kwj.jooqt.domain.generated.public_.tables.Book.BOOK;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final DSLContext dslContext;

    public Book selectById(int id) {
        return dslContext.select()
                .from(BOOK)
                .where(BOOK.ID.eq(id))
                .fetchOneInto(Book.class);
    }

    public List<Book> selectAll() {
        return dslContext.select()
                .from(BOOK)
                .fetchInto(Book.class);
    }

}

package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Author;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kwj.jooqt.domain.generated.public_.tables.Author.AUTHOR;

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

}

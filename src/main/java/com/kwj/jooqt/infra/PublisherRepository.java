package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Publisher;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

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

}

package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Publisher;
import com.kwj.jooqt.domain.generated.public_.tables.JPublisher;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PublisherRepository {

    private final DSLContext dslContext;

    public Publisher selectById(int id) {
        return dslContext.select()
                .from(JPublisher.PUBLISHER)
                .where(JPublisher.PUBLISHER.ID.eq(id))
                .fetchOneInto(Publisher.class);
    }

}

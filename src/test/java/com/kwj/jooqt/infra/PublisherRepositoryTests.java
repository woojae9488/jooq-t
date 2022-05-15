package com.kwj.jooqt.infra;

import com.kwj.jooqt.domain.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql("classpath:/sql/jooq_test_init.sql")
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

}
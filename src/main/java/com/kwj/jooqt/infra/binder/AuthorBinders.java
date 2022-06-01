package com.kwj.jooqt.infra.binder;

import com.kwj.jooqt.domain.Author;
import com.kwj.jooqt.domain.Book;
import com.kwj.jooqt.domain.value.AuthorBooksKey;
import com.kwj.jooqt.infra.converter.AuthorBirthdayConverter;
import org.jooq.Converter;
import org.jooq.Record;
import org.jooq.RecordMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.kwj.jooqt.domain.generated.public_.tables.Author.AUTHOR;
import static com.kwj.jooqt.domain.generated.public_.tables.Book.BOOK;

public class AuthorBinders {

    private static final Converter<String, String> BIRTHDAY_CONVERTER = new AuthorBirthdayConverter();

    public static final RecordMapper<? super Record, Author> authorRecordMapper =
            record -> Author.builder()
                    .id(record.get(AUTHOR.ID))
                    .publisherId(record.get(AUTHOR.PUBLISHER_ID))
                    .name(record.get(AUTHOR.NAME))
                    .birthday(record.get(AUTHOR.BIRTHDAY, BIRTHDAY_CONVERTER))
                    .gender(record.get(AUTHOR.GENDER).charAt(0))
                    .build();

    public static final Collector<? super Record, ?, Map<AuthorBooksKey, List<Book>>>
            authorBooksMapCollector = Collectors.groupingBy(
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
            ));

}

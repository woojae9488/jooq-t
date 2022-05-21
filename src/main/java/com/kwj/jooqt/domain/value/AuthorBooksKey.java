package com.kwj.jooqt.domain.value;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AuthorBooksKey {

    @EqualsAndHashCode.Include
    private final Integer id;

    private final String name;

}

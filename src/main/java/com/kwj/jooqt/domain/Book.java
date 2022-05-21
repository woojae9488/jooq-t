package com.kwj.jooqt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Book {

    private final Integer id;

    private final int authorId;

    private final String title;

    private final int price;

}

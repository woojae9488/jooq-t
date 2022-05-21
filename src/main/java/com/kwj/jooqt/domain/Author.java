package com.kwj.jooqt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Author {

    private final Integer id;

    private final int publisherId;

    private final String name;

    private final String birthday;

    private final char gender;

}

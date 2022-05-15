package com.kwj.jooqt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Author {

    private Integer id;

    private int publisherId;

    private String name;

    private String birthday;

    private char gender;

}

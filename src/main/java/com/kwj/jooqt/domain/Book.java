package com.kwj.jooqt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    private Integer id;

    private int authorId;

    private String title;

    private int price;

}

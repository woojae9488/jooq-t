package com.kwj.jooqt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Publisher {

    private final Integer id;

    private final String name;

    private final String address;

}

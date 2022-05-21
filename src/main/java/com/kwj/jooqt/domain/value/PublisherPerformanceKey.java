package com.kwj.jooqt.domain.value;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PublisherPerformanceKey {

    @EqualsAndHashCode.Include
    private final Integer id;

    private final String name;

    private final String address;

}

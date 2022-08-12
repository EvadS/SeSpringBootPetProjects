package com.se.maual.pagging.enums;

import org.springframework.data.domain.Sort;

import java.util.stream.Stream;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */
public enum Direction {

    ASCENDING("asc", Sort.Direction.ASC),
    DESCENDING("desc", Sort.Direction.DESC);
    private final String directionCode;
    private final Sort.Direction direction;

    private Direction(String directionCode, Sort.Direction direction) {
        this.directionCode = directionCode;
        this.direction = direction;
    }

    public String getDirectionCode() {
        return this.directionCode;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public static Direction of(String direction) {
        return Stream.of(Direction.values())
                .filter(p -> p.getDirectionCode().equals(direction))
                .findFirst()
                .orElse(DESCENDING);
    }
}


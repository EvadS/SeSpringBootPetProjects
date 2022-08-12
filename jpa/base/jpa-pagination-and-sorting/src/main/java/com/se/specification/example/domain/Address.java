package com.se.specification.example.domain;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @Column
    private Integer zip;

    @Column
    private String street;

    @Column
    private String city;
}

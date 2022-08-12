package com.se.specification.example.controller.dto;

import java.util.List;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */public class UserDto {
    public Long id;
    public String email;
    public String firstName;
    public String lastName;
    public List<AddressDto> addresses;
}
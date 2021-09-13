package com.example.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Skiba Evgeniy
 * @date 13.09.2021
 */
@Data
@ToString
public class UserDto {

    private String firstName;

    @NotNull(message = "Last name can not be empty")
    private String lastName;

    @Min(value = 10, message = "Required min age is 10")
    @Max(value = 50, message = "Required max age is 50")
    private int age;

    @Pattern(regexp = "([a-z])+@([a-z])+\\.com", message = "Email should match the pattern a-z @ a-z .com")
    private String email;

}
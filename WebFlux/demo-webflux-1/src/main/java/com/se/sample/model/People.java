package com.se.sample.model;

import com.se.sample.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class People {
    private String name;
    private Integer age;
    private Sex sex;


}

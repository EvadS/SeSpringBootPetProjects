package com.se.sample.note.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}

package com.se.sample.rest.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Table("positions")
public class Position {

    @Id
    private String id;

    @NotBlank
    @Size(max = 140)
    private String positionName;

    private String description;

    @NotNull
    private Date createdAt = new Date();

}
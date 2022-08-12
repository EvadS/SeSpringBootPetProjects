package com.se.sample.componenets;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusItem {
    private String job;
    private String status;
    private int count;
}

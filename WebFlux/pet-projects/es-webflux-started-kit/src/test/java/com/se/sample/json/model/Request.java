package com.se.sample.json.model;


import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Request
{
    private Car car;
    private Date datePurchased;
    private LocalDateTime updatedAt;

    private Instant createdDate;
}

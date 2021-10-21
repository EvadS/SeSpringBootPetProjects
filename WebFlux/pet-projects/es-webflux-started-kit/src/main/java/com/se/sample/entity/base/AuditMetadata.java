package com.se.sample.entity.base;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

public abstract class AuditMetadata implements Serializable {

//    @CreatedDate
//    private Instant createdDate;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}

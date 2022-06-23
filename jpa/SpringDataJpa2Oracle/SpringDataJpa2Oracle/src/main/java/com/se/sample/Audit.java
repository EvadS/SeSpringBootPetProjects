package com.se.sample;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@Embeddable
public class Audit {

    @Column(name = "CREATED_DATE", updatable = false)
    protected LocalDateTime createdDate;

    @Column(name = "UPDATED_DATE")
    protected LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}

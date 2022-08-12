package com.se.spec.dto;

import java.util.Date;

public class NoteRequest {
    public long startedPeriod;
    public long endPeriod;

    public long getStartedPeriod() {
        return startedPeriod;
    }

    public void setStartedPeriod(long startedPeriod) {
        this.startedPeriod = startedPeriod;
    }

    public long getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(long endPeriod) {
        this.endPeriod = endPeriod;
    }
}

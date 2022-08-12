package com.se.nil.embedded.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ID implements Serializable {

    public ID(long pid, long sid) {
        this.pid = pid;
        this.sid = sid;
    }

    private long pid;

    @Column(nullable=false)
    private long sid;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public ID() {
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID id = (ID) o;
        return pid == id.pid &&
                sid == id.sid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, sid);
    }
}
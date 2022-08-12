package com.se.nil.embedded.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @EmbeddedId
    private ID id;

    private String comment;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "office_pid", referencedColumnName = "pid"),
            @JoinColumn(name = "office_sid", referencedColumnName = "sid")
    })
    private Office officeTo;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "office_pid_from", referencedColumnName = "pid"),
            @JoinColumn(name = "office_sid_from", referencedColumnName = "sid")
    })
    private Office officeFrom;

//    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.LAZY)
//    @JoinColumns({
//            @JoinColumn(name = "setting_pid", referencedColumnName = "pid", updatable=false, insertable=false),
//            @JoinColumn(name = "setting_sid", referencedColumnName = "sid", updatable=false, insertable=false)
//    })
//    private Settings setting;


    public Office getOfficeTo() {
        return officeTo;
    }

    public void setOfficeTo(Office officeTo) {
        this.officeTo = officeTo;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Office getOfficeFrom() {
        return officeFrom;
    }

    public void setOfficeFrom(Office officeFrom) {
        this.officeFrom = officeFrom;
    }
}
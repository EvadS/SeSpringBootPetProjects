package com.se.nil.embedded.entity;

import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {

    @EmbeddedId
    private ID id;

    @Column(length = 16)
    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "user_pid", referencedColumnName = "pid"),
            @JoinColumn(name = "user_sid", referencedColumnName = "sid")
    })


    private User user;


}
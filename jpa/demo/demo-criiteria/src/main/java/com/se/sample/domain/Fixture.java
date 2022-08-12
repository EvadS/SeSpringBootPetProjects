package com.se.sample.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity(name = "My_Fixture")
@Table(name = "my_fixture")
public class Fixture {
    @Id
    @Column(unique = true, nullable = false)
    @NotNull
    private String id;

    private String name;

    /**
     * League name.
     */
    private String league;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sportId")
    @Fetch(FetchMode.JOIN)
    private Sport sport;

    /**
     * Event start time in Date format. Can be unset.
     */
    private Date eventDate;

    /**
     * Not set for now
     */
    private String country;

    /**
     * Event time stamp in seconds.
     */
    private long eventTimestamp;

    public Fixture() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }


    @Override
    public String toString() {
        return "Fixture{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", league='" + league + '\'' +
                ", eventDate=" + eventDate +
                ", country='" + country + '\'' +
                ", eventTimestamp=" + eventTimestamp +
                '}';
    }
}
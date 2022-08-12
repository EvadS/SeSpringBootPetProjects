package com.se.element.collection.model;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
@Table(name = "skill")
public class SearchersSkills {
    @NotNull
    private String skill;

    @NotNull
    @Size(max = 100)
    private String value;

    public SearchersSkills() {
    }

    public SearchersSkills(@NotNull String skill, @NotNull @Size(max = 100) String value) {
        this.skill = skill;
        this.value = value;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "skill {" +
                "skill='" + skill + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

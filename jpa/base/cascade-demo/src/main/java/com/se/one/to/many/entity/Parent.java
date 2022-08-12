package com.se.one.to.many.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "parent"
           // ,cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY
           // ,orphanRemoval = true
    )
    private Set<Child> attachments = new HashSet<Child>();

    public Parent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Child> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Child> attachments) {
        this.attachments = attachments;
    }

    public void addChild(Child comment) {
        attachments.add(comment);
        comment.setParent(this);
    }

    public void removeChild(Child comment) {
        attachments.remove(comment);
        comment.setParent(null);
    }

    @Override
    public String toString() {
        return "'\t=== \tParent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
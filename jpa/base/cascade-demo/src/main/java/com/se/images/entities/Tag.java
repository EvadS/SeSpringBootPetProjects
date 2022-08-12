package com.se.images.entities;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Tags for fast search images in Image gallery in Admin-bo
 */
@Entity(name = "Tag")
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NaturalId
    private String tag;

    @OneToMany(mappedBy = "tag",fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
          //  CascadeType.PERSIST,
            CascadeType.REFRESH })
    private final Set<ImageTag> imageTags = new HashSet<>();

    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<ImageTag> getImageTags() {
        return imageTags;
    }

    public void addImageTag(ImageTag labelOdd) {
        this.imageTags.add(labelOdd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tag, tag.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag='" + tag + '\'' + '}';
    }
}

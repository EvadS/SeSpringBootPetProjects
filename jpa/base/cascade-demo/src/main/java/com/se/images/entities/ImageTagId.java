package com.se.images.entities;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ImageTagId implements Serializable {

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "tag_id")
    private Long tagId;

    public ImageTagId() {
    }

    public ImageTagId(
            Long imageId,
            Long tagId) {
        this.imageId = imageId;
        this.tagId = tagId;
    }

    public Long getImageId() {
        return imageId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageTagId that = (ImageTagId) o;
        return Objects.equals(imageId, that.imageId) &&
                Objects.equals(tagId, that.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, tagId);
    }

    @Override
    public String toString() {
        return "ImageTagId{" +
                "imageId=" + imageId +
                ", tagId=" + tagId +
                '}';
    }
}
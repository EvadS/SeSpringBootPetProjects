package com.se.images.entities;

import javax.persistence.*;


@Entity(name = "ImageTag")
@Table(name = "image_tag")
public class ImageTag {

    @EmbeddedId
    private ImageTagId id;

    @ManyToOne(cascade= CascadeType.REFRESH)
    @MapsId("imageId")
    private Image image;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @MapsId("tagId")
    private Tag tag;

    public ImageTag() {
    }

    public ImageTag(Image image, Tag tag) {
        this.image = image;
        this.tag = tag;
        this.id = new ImageTagId(image.getId(), tag.getId());
    }

    public ImageTagId getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}

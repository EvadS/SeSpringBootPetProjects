package com.se.images.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Image")
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Original name of file.
     */
    private String originName;
    /**
     * Path on S3 for dev usages. Images deleted from s3 by this path.
     */
    private String path3;
    /**
     * Public image url. In future this should be passed via CloudFront to access faster access to resources.
     */
    private String imageUrl;

    /**
     * Image tags
     */
    @OneToMany(mappedBy = "image",
           cascade = {CascadeType.MERGE},
           //  cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<ImageTag> imageTags = new HashSet<>();


    public Image() {
    }

    public Image(String originName, String path3, String imageUrl) {
        this.originName = originName;
        this.path3 = path3;
        this.imageUrl = imageUrl;
    }

    public String getOriginName() {
        return originName;
    }

    public Image setOriginName(String originName) {
        this.originName = originName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Image setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getPath3() {
        return path3;
    }

    public Image setPath3(String path3) {
        this.path3 = path3;
        return this;
    }

    public Set<ImageTag> getImageTags() {
        return imageTags;
    }

    public void setImageTags(Set<ImageTag> imageTags) {
        this.imageTags = imageTags;
    }

    public void addImageTag(ImageTag imageTag) {
        this.imageTags.add(imageTag);
    }

    public void removeTag(Tag tag) {
        for (Iterator<ImageTag> iterator = imageTags.iterator();
             iterator.hasNext(); ) {
            ImageTag imageTag = iterator.next();

            if (imageTag.getImage().equals(this) &&
                    imageTag.getTag().equals(tag)) {
                iterator.remove();
                imageTag.getTag().getImageTags().remove(imageTag);
                imageTag.setImage(null);
                imageTag.setTag(null);
            }
        }
    }


    public void removeImageTag(ImageTag imageTag) {
        imageTag.setImage(null);
        this.imageTags.remove(imageTag);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Image image = (Image) o;
        return Objects.equals(originName, image.originName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originName);
    }


    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", originName='" + originName + '\'' +
                ", path3='" + path3 + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

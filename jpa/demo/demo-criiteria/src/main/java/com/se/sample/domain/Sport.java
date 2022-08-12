package com.se.sample.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Sport information. Used for storing sport name, sport assets and ids in providers.
 */
@Entity(name = "Sport")
@Table(name = "sport")
public class Sport extends AbstractEntity {
    private String name;
    @Column(nullable = false)
    private String nameKey; // same as in entities upperCase of name
    @Column
    private long idBet365;

    private long orderIndex;

    private long imageId;
    private String imageUrl;

    private long bgLeftId;
    private long bgRightId;
    private String bgLeftUrl;
    private String bgRightUrl;

    @Column(columnDefinition = "boolean default false")
    private boolean visible;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(long orderIndex) {
        this.orderIndex = orderIndex;
    }

    public long getBgLeftId() {
        return bgLeftId;
    }

    public void setBgLeftId(long bgLeftId) {
        this.bgLeftId = bgLeftId;
    }

    public long getBgRightId() {
        return bgRightId;
    }

    public void setBgRightId(long bgRightId) {
        this.bgRightId = bgRightId;
    }

    public String getBgLeftUrl() {
        return bgLeftUrl;
    }

    public void setBgLeftUrl(String bgLeftUrl) {
        this.bgLeftUrl = bgLeftUrl;
    }

    public String getBgRightUrl() {
        return bgRightUrl;
    }

    public void setBgRightUrl(String bgRightUrl) {
        this.bgRightUrl = bgRightUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isVisible() {
        return visible;
    }

    public Sport setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public long getIdBet365() {
        return idBet365;
    }

    public void setIdBet365(long idBet365) {
        this.idBet365 = idBet365;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", nameKey='" + nameKey + '\'' +
                ", orderIndex=" + orderIndex +
                '}';
    }
}

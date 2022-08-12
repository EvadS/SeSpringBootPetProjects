package com.se.sample.model.base;

/**
 * Created by Evgeniy Skiba on 19.03.21
 */
public class Item {
    public int id;
    public String itemName;
    public User owner;

    public Item() {
        super();
    }

    public Item(final int id, final String itemName, final User owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }
}
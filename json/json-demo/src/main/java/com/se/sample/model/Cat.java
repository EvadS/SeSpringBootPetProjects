package com.se.sample.model;

/**
 * Created by Evgeniy Skiba on 19.03.21
 */
public class Cat extends Animal {
    public int lives;

    public Cat() { }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
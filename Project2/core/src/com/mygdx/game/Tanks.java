package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tanks extends Rectangle implements Cloneable {
    private Texture tankImg;
    private Texture canonImg;

    public Texture getHealthImg() {
        return healthImg;
    }

    public void setHealthImg(Texture healthImg) {
        this.healthImg = healthImg;
    }

    public Texture getName1() {
        return name1;
    }

    public void setName1(Texture name1) {
        this.name1 = name1;
    }

    private Texture healthImg;
    private Texture name1;

    private String name;
    private int health;
    private int fuel;

    public Texture getTankImg() {
        return tankImg;
    }

    public void setTankImg(Texture tankImg) {
        this.tankImg = tankImg;
    }

    public Texture getCanonImg() {
        return canonImg;
    }

    public void setCanonImg(Texture canonImg) {
        this.canonImg = canonImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getX_cord() {
        return x_cord;
    }

    public void setX_cord(int x_cord) {
        this.x_cord = x_cord;
    }

    public int getY_cord() {
        return y_cord;
    }

    public void setY_cord(int y_cord) {
        this.y_cord = y_cord;
    }

    private int x_cord;
    private int y_cord;

    public Tanks(Texture img,Texture canon,String name,int health,Texture health1,Texture name1 ){
        this.tankImg=img;
        this.canonImg=canon;
        this.name=name;
        this.health=health;
        this.fuel=150;
        this.name1=name1;
        this.healthImg=health1;
    }

    public void move(){

    }

    public void fire(){

    }

    public Object clone() {
        return super.clone();
    }
}

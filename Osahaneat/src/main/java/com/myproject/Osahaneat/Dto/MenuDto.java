package com.myproject.Osahaneat.Dto;

public class MenuDto {
    private String title;
    private String image;
    //private String timpeShip;
    private boolean isFreeship;
    //private double price;
    //private int cateId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFreeship() {
        return isFreeship;
    }

    public void setFreeship(boolean freeship) {
        isFreeship = freeship;
    }
}

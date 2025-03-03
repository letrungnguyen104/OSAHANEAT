package com.myproject.Osahaneat.Dto;

import java.util.Date;
import java.util.List;

public class RestaurantDto {
    private String image;
    private String title;
    private double rating;
    private String subtitle;
    private boolean isFreeship;
    private Date openDate;
    List<CategoryDto> listCate;

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public List<CategoryDto> getListCate() {
        return listCate;
    }

    public void setListCate(List<CategoryDto> listCate) {
        this.listCate = listCate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isFreeship() {
        return isFreeship;
    }

    public void setFreeship(boolean freeship) {
        isFreeship = freeship;
    }
}

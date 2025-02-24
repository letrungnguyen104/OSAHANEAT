package com.myproject.Osahaneat.Dto;

import java.util.List;

public class CategoryDto {
    private String name;
    private List<MenuDto> listMenuDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuDto> getListMenuDto() {
        return listMenuDto;
    }

    public void setListMenuDto(List<MenuDto> listMenuDto) {
        this.listMenuDto = listMenuDto;
    }
}

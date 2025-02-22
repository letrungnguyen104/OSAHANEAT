package com.myproject.Osahaneat.Service.Imp;

import org.springframework.web.multipart.MultipartFile;

public interface MenuServiceImp {
    public boolean createMenu(MultipartFile file,
                              String title,
                              String timeShip,
                              boolean isFreeship,
                              double price,
                              int cateId);
}

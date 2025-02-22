package com.myproject.Osahaneat.Service.Imp;

import com.myproject.Osahaneat.Dto.RestaurantDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface RestaurantServiceImp {
    public boolean insertRestaurant(MultipartFile file,
                                    String title,
                                    String subtitle,
                                    String description,
                                    boolean isFreeship,
                                    String address,
                                    String openDate);
    public List<RestaurantDto> getHomePageRestaurant();
}

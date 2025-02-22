package com.myproject.Osahaneat.Service;

import com.myproject.Osahaneat.Dto.RestaurantDto;
import com.myproject.Osahaneat.Entity.RatingRestaurant;
import com.myproject.Osahaneat.Entity.Restaurant;
import com.myproject.Osahaneat.Repository.RestaurantRepository;
import com.myproject.Osahaneat.Service.Imp.FileServiceImp;
import com.myproject.Osahaneat.Service.Imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RestaurantService implements RestaurantServiceImp {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileServiceImp fileServiceImp;

    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean isFreeship, String address, String openDate) {
        boolean isInsertSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setDesc(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeship(isFreeship);
                restaurant.setAddress(address);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date date = simpleDateFormat.parse(openDate);
                restaurant.setOpenDate(date);
                restaurantRepository.save(restaurant);
                isInsertSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
        return isInsertSuccess;
    }

    @Override
    public List<RestaurantDto> getHomePageRestaurant() {
        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);
        List<RestaurantDto> listRestaurantDto = new ArrayList<>();
        for(Restaurant restaurant : listData){
            RestaurantDto restaurantDto = new RestaurantDto();
            restaurantDto.setImage(restaurant.getImage());
            restaurantDto.setTitle(restaurant.getTitle());
            restaurantDto.setFreeship(restaurant.isFreeship());
            restaurantDto.setSubtitle(restaurant.getSubtitle());
            restaurantDto.setRating(calculatorRating(restaurant.getListRatingRestaurant()));
            listRestaurantDto.add(restaurantDto);
        }
        return listRestaurantDto;
    }

    private double calculatorRating(Set<RatingRestaurant> list){
        double totalPoint = 0;
        for(RatingRestaurant ratingRestaurant : list){
            totalPoint += ratingRestaurant.getRatePoint();
        }
        return totalPoint / list.size();
    }
}

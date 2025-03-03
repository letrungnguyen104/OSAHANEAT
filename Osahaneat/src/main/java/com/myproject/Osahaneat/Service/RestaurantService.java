package com.myproject.Osahaneat.Service;

import com.myproject.Osahaneat.Dto.CategoryDto;
import com.myproject.Osahaneat.Dto.MenuDto;
import com.myproject.Osahaneat.Dto.RestaurantDto;
import com.myproject.Osahaneat.Entity.*;
import com.myproject.Osahaneat.Repository.RestaurantRepository;
import com.myproject.Osahaneat.Service.Imp.FileServiceImp;
import com.myproject.Osahaneat.Service.Imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Override
    public RestaurantDto getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantDto restaurantDto = new RestaurantDto();
        if(restaurant.isPresent()){
            List<CategoryDto> listCate = new ArrayList<>();
            restaurantDto.setImage(restaurant.get().getImage());
            restaurantDto.setTitle(restaurant.get().getTitle());
            restaurantDto.setSubtitle(restaurant.get().getSubtitle());
            restaurantDto.setRating(calculatorRating(restaurant.get().getListRatingRestaurant()));
            restaurantDto.setFreeship(restaurant.get().isFreeship());
            restaurantDto.setOpenDate(restaurant.get().getOpenDate());

            //Duyệt qua MenuRestaurant để lấy từng Category
            for(MenuRestaurant menuRestaurant : restaurant.get().getListMenuRestaurant()) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setName(menuRestaurant.getCategory().getNameCate());
                List<MenuDto> listMenuDto = new ArrayList<>();
                //Mỗi category sẽ có một List<Food> nên duyệt qua List<Food> để gán lại cho từng MenuDto
                for(Food food : menuRestaurant.getCategory().getListFood()){
                    MenuDto menuDto = new MenuDto();
                    menuDto.setImage(food.getImage());
                    menuDto.setTitle(food.getTitle());
                    menuDto.setFreeship(food.isFreeship());
                    listMenuDto.add(menuDto);
                }
                categoryDto.setListMenuDto(listMenuDto);
                listCate.add(categoryDto);
            }

            restaurantDto.setListCate(listCate);
        }
        return restaurantDto;
    }
}

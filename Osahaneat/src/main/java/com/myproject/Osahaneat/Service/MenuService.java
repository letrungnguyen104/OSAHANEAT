package com.myproject.Osahaneat.Service;

import com.myproject.Osahaneat.Entity.Category;
import com.myproject.Osahaneat.Entity.Food;
import com.myproject.Osahaneat.Repository.FoodRepository;
import com.myproject.Osahaneat.Service.Imp.FileServiceImp;
import com.myproject.Osahaneat.Service.Imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MenuService implements MenuServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public boolean createMenu(MultipartFile file, String title, String timeShip, boolean isFreeship, double price, int cateId) {
        boolean isInsertSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Food food = new Food();
                food.setImage(file.getOriginalFilename());
                food.setTitle(title);
                food.setTimeShip(timeShip);
                food.setFreeship(isFreeship);
                food.setPrice(price);
                Category category = new Category();
                category.setId(cateId);
                food.setCategory(category);
                foodRepository.save(food);
            }
            isInsertSuccess = true;
        } catch (Exception e) {
            System.out.println("Insert Failure!");
        }

        return isInsertSuccess;
    }
}

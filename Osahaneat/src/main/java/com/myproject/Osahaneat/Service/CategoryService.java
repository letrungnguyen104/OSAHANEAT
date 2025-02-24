package com.myproject.Osahaneat.Service;

import com.myproject.Osahaneat.Dto.CategoryDto;
import com.myproject.Osahaneat.Dto.MenuDto;
import com.myproject.Osahaneat.Entity.Category;
import com.myproject.Osahaneat.Entity.Food;
import com.myproject.Osahaneat.Repository.CategoryRepository;
import com.myproject.Osahaneat.Service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getCategoryHomepage() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("id"));
        Page<Category> listCategory = categoryRepository.findAll(pageRequest);
        List<CategoryDto> listCategoryDto = new ArrayList<>();
        for(Category category : listCategory){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getNameCate());
            List<MenuDto> listMenuDto = new ArrayList<>();
            for(Food food : category.getListFood()){
                MenuDto menuDto = new MenuDto();
                menuDto.setImage(food.getImage());
                menuDto.setTitle(food.getTitle());
                menuDto.setFreeship(food.isFreeship());
                listMenuDto.add(menuDto);
            }
            categoryDto.setListMenuDto(listMenuDto);
            listCategoryDto.add(categoryDto);
        }
        return listCategoryDto;
    }
}

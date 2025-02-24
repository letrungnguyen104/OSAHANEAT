package com.myproject.Osahaneat.Controller;

import com.myproject.Osahaneat.Dto.CategoryDto;
import com.myproject.Osahaneat.Payload.ResponseData;
import com.myproject.Osahaneat.Service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryServiceImp;

    @GetMapping()
    public ResponseEntity<?> getCategoryHomepage(){
        ResponseData responseData = new ResponseData();
        List<CategoryDto> listCategoryDto = categoryServiceImp.getCategoryHomepage();
        responseData.setData(listCategoryDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}

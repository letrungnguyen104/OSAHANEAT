package com.myproject.Osahaneat.Controller;

import com.myproject.Osahaneat.Payload.ResponseData;
import com.myproject.Osahaneat.Service.FileService;
import com.myproject.Osahaneat.Service.Imp.FileServiceImp;
import com.myproject.Osahaneat.Service.Imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    RestaurantServiceImp restaurantServiceImp;

    @PostMapping("/upload")
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
                                              @RequestParam String title,
                                              @RequestParam String subtitle,
                                              @RequestParam String description,
                                              @RequestParam boolean isFreeship,
                                              @RequestParam String address,
                                              @RequestParam String openDate){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = restaurantServiceImp.insertRestaurant(file, title, subtitle, description, isFreeship, address, openDate);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getRestaurant(){
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantServiceImp.getHomePageRestaurant());
        return new ResponseEntity<>(responseData.getData(), HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String filename){
        Resource resource = fileServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

}

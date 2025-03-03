package com.myproject.Osahaneat.Controller;

import com.myproject.Osahaneat.Payload.ResponseData;
import com.myproject.Osahaneat.Service.Imp.FileServiceImp;
import com.myproject.Osahaneat.Service.Imp.MenuServiceImp;
import com.myproject.Osahaneat.Service.Imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    RestaurantServiceImp restaurantServiceImp;

    @Autowired
    MenuServiceImp menuServiceImp;

    @PostMapping("/upload")
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
                                              @RequestParam String title,
                                              @RequestParam String timeShip,
                                              @RequestParam boolean isFreeship,
                                              @RequestParam double price,
                                              @RequestParam int cateId){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = menuServiceImp.createMenu(file, title, timeShip, isFreeship, price, cateId);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileMenu(@PathVariable String filename){
        Resource resource = fileServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}

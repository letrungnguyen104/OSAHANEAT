package com.myproject.Osahaneat.Controller;

import com.myproject.Osahaneat.Service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userServiceImp.getAllUser(), HttpStatus.OK);
    }

}

package com.myproject.Osahaneat.Controller;

import com.myproject.Osahaneat.Payload.Request.SignupRequest;
import com.myproject.Osahaneat.Payload.ResponseData;
import com.myproject.Osahaneat.Service.Imp.LoginServiceImp;
import com.myproject.Osahaneat.Service.LoginService;
import com.myproject.Osahaneat.Utils.JwtUtilsHelper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
//  @Qualifier("ten bean") - de chi dinh dung ten class neu co nhieu hon 2 class o container
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();

        //Nếu đăng nhập thành công thì sinh ra token
        if(loginServiceImp.checkLogin(username, password)){
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setData(token);
        }
        else{
            responseData.setData("");
            responseData.setSuccess(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    //Dang ki tai khoan nen nguoi dung se truyen tham so duoi dang JSON => @RequestBody
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        ResponseData responseData = new ResponseData();
        if(loginServiceImp.addUser(signupRequest)){
            responseData.setData(true);
        }
        else{
            responseData.setData(false);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/get-all-user")
    public ResponseEntity<?> getAllUser(){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.getAllUser());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}

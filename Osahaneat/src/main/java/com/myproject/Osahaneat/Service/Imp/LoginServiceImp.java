package com.myproject.Osahaneat.Service.Imp;

import com.myproject.Osahaneat.Dto.UserDto;
import com.myproject.Osahaneat.Payload.Request.SignupRequest;

import java.util.List;

public interface LoginServiceImp {
    public List<UserDto> getAllUser();
    public boolean checkLogin(String username, String password);
    public boolean addUser(SignupRequest signupRequest);
}

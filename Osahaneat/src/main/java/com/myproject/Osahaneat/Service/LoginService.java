package com.myproject.Osahaneat.Service;

import com.myproject.Osahaneat.Dto.UserDto;
import com.myproject.Osahaneat.Entity.Roles;
import com.myproject.Osahaneat.Entity.Users;
import com.myproject.Osahaneat.Payload.Request.SignupRequest;
import com.myproject.Osahaneat.Repository.UserRepository;
import com.myproject.Osahaneat.Service.Imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUser(){
        List<Users> listUser = userRepository.findAll();
        List<UserDto> listUserDto = new ArrayList<>();
        for(Users user : listUser){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUserName(user.getUserName());
            userDto.setPassword(user.getPassword());
            userDto.setFullname(user.getFullname());
            userDto.setCreateDate(user.getCreateDate());
            listUserDto.add(userDto);
        }
        return listUserDto;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        Users user =  userRepository.findByUserName(username);
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public boolean addUser(SignupRequest signupRequest) {
        Users user = new Users();
        Roles roles = new Roles();
        roles.setId(signupRequest.getRoleId());
        user.setFullname(signupRequest.getFullname());
        user.setUserName(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setRoles(roles);
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

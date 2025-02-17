package com.myproject.Osahaneat.Service;

import com.myproject.Osahaneat.Dto.UserDto;
import com.myproject.Osahaneat.Entity.Users;
import com.myproject.Osahaneat.Repository.UserRepository;
import com.myproject.Osahaneat.Service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getAllUser() {
        List<Users> listUser = userRepository.findAll();
        List<UserDto> listUserDto = new ArrayList<>();
        for(Users users : listUser){
            UserDto userDto = new UserDto();
            userDto.setId(users.getId());
            userDto.setFullname(users.getFullname());
            userDto.setUserName(users.getUserName());
            userDto.setPassword(users.getPassword());
            userDto.setCreateDate(users.getCreateDate());
            listUserDto.add(userDto);
        }
        return listUserDto;
    }
}

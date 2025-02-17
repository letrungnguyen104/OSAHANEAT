package com.myproject.Osahaneat.Security;

import com.myproject.Osahaneat.Entity.Users;
import com.myproject.Osahaneat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Đem username được truyen vào để đi lấy username, pass
        Users users = userRepository.findByUserName(username);
        if(users == null){
            throw new UsernameNotFoundException("Username does not exist!");
        }
        //Nêu tồn tại username trên thì đem username truyền vào + password tương ứng trong database đi check
        return new User(username, users.getPassword(), new ArrayList<>());
    }
}

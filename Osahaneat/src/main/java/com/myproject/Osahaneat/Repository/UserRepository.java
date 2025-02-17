package com.myproject.Osahaneat.Repository;

import com.myproject.Osahaneat.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    public List<Users> findByUserNameAndPassword(String username, String password);
    public Users findByUserName(String username);
}

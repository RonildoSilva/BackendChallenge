package com.example.user.configuration.security;

import java.util.ArrayList;

import com.example.user.endpoint.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.domain.model.User user_model = userService.findByLogin(username);


        if((!user_model.equals(null)) && user_model.getAdmin().equals(true)){
            return new User(user_model.getLogin(), user_model.getPassword(),
                    new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
package com.smartcontactmanager.config;

import com.smartcontactmanager.Entity.User;
import com.smartcontactmanager.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Fetching user from database
        User user=userRepository.getUserByUserName(username);
        if(user==null)
        {
            throw  new UsernameNotFoundException("Could not found user");
        }

        CustomUserDetails customUserDetails=new CustomUserDetails(user);

        return customUserDetails;
    }
}
package com.smartcontactmanager.SmartController;

import com.smartcontactmanager.Entity.User;
import com.smartcontactmanager.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    public String dashboard(Model model, Principal principal)
    {
        String userName=principal.getName();
        System.out.println("username-" +userName);

        User user=userRepository.getUserByUserName(userName);

        System.out.println("User-" +user);
        model.addAttribute("user",user);
        //get the user using username(Email)
        return "normal/user_dashboard";
    }

}

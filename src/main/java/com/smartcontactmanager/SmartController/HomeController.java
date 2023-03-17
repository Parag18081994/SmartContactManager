package com.smartcontactmanager.SmartController;

import com.smartcontactmanager.Entity.User;
import com.smartcontactmanager.dao.UserRepository;
import com.smartcontactmanager.helper.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home-Smart contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About-Smart contact Manager");
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register-Smart contact Manager");
        model.addAttribute("user", new User());
        return "signup";
    }

    //handler for regestering user
    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {
        model.addAttribute("title","Home-Smart Contact Manager");
        try {
            if (!agreement) {
                System.out.println("You have not agreed to T&C");
                throw new Exception("You have not agreed to T&C"); //if we are not agreed it will go in catch block
            }

            if(result1.hasErrors())
            {
                System.out.println("ERROR "+result1.toString());
                model.addAttribute("user",user);
                return "signup";
            }


            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("default.png");

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            System.out.println("Agreement " + agreement);
            System.out.println("User" + user);

            User result = this.userRepository.save(user);   //user will be saved in database

            model.addAttribute("user", new User()); //field will come blank once user is saved
            session.setAttribute("message", new Message("Successfully register", "alert-success"));
            return "signup";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong" + e.getMessage(), "alert-danger"));
            return "signup";
        }


    }

    //handler for custome login
    @GetMapping("/signin")
    public  String customeLogin(Model model)
    {
        model.addAttribute("title","Home-Smart Contact Manager");
        return "login";
    }

}

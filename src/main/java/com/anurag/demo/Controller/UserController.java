package com.anurag.demo.Controller;

import com.anurag.demo.Entity.User;
import com.anurag.demo.Repository.UserRepository;
import com.anurag.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;



    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hello World";
    }

    @PostMapping("/add")

    public String addnewUser(@RequestBody User user)
    {
    userService.register(user);
    return "Great Success";
    }

    @PostMapping("/login")

    public String login(@RequestBody User user)
    {
       return userService.Login(user);


    }

}

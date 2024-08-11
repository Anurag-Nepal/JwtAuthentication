package com.anurag.demo.Controller;

import com.anurag.demo.Entity.User;
import com.anurag.demo.Repository.UserRepository;
import com.anurag.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@GetMapping("/")
public String Hey()
{
    return "Hey Ur Oauth2 Client is properly Functioning Mate";
}

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

    public ResponseEntity<String> login(@RequestBody User user)
    {
       return userService.Login(user);


    }

}

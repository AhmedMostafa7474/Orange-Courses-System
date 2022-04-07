package com.example.Spring1.Controller;

import com.example.Spring1.Model.User;
import com.example.Spring1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') ")
    public List<User> GetUsers(@RequestHeader String Authorization)
    {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public User getUserInfo(@PathVariable int id,@RequestHeader String Authorization)
    {
      return userService.getUserInfo(id);
    }


    @PostMapping("/add")
    public void AddUser(@RequestBody User user)
    {
        userService.adduser(user);
    }
    @DeleteMapping("/user/{id}")
    public void DeleteUser(@PathVariable int id)
    {
        userService.deleteuser(id);
    }

}

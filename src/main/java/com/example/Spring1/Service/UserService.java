package com.example.Spring1.Service;

import com.example.Spring1.Controller.LoginController;
import com.example.Spring1.Model.Adminn;
import com.example.Spring1.Model.MyAdminDetails;
import com.example.Spring1.Model.MyUserDetails;
import com.example.Spring1.Model.User;
import com.example.Spring1.Repo.AdminnRepo;
import com.example.Spring1.Repo.EnrollRepo;
import com.example.Spring1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService  implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    AdminnRepo adminnRepo;
    @Autowired
    private EnrollRepo enrollRepo;

    public UserService() {
    }

    public List<User> getUsers() {
        List<User>users=new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        users.forEach(user -> user.getCourses().forEach(course -> course.getExams().forEach(exam -> exam.setCourse(null))));
        return users;
    }

    public User getUserInfo(int id) {
        User user= userRepo.findById(id).get();
        user.getCourses().forEach(course -> course.getExams().forEach(exam -> exam.setCourse(null)));
        return user;
    }
    public void adduser(User user)
    {
        userRepo.save(user);
    }
    public void deleteuser(int id) {
        userRepo.deleteById(id);
    }


    @Override
    public MyUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       if(LoginController.auth==1)
       {
           Optional<User>user= userRepo.findByStudentName(s);
           return new MyUserDetails(user.get(),"ROLE_USER");
       }
       else
       {
            Optional<Adminn>user2=adminnRepo.findByUsername(s);
            System.out.println(user2.get().getRole());
            return new MyUserDetails(user2.get(),user2.get().getRole());
       }
    }

    public Optional<User> loadUserByemail(String s) {
        Optional<User>user= userRepo.findByEmail(s);
        return user;
    }
}

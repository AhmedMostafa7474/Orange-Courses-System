package com.example.Spring1.Controller;

import com.example.Spring1.Model.AuthRequest;
import com.example.Spring1.Model.AuthResponse;
import com.example.Spring1.Model.User;
import com.example.Spring1.Repo.UserRepo;
import com.example.Spring1.Security.JwtUtil;
import com.example.Spring1.Service.AdminnServices;
import com.example.Spring1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;

@RestController
public class LoginController {
    public static int auth=0;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    AdminnServices adminnServices;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AuthRequest authRequest)
    {
        auth=1;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );
        UserDetails userDetails=userService.loadUserByUsername(authRequest.getUsername());
        final String jwt= jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/authAdmin")
    public ResponseEntity<?> authAdmin(@RequestBody AuthRequest authRequest)
    {  auth=2;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );
        UserDetails userDetails=adminnServices.loadUserByUsername(authRequest.getUsername());
        final String jwt= jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, String>> registerNewUserAccount(@RequestBody User u){
        HashMap<String, String> map= new HashMap<>();
        if (userService.loadUserByemail(u.getEmail()).equals("NoOptional.empty")
        ||userRepo.findByStudentName(u.getStudent_name()).equals("NoOptional.empty")) {
            return null;
        }
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setCreate_at(new Timestamp(System.currentTimeMillis()));
        userRepo.save(u);
        map.put("message","success");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
//    @RequestMapping("/")
//    public String all()
//    {
//        return "all";
//    }
//    @RequestMapping("/user")
//    public String user()
//    {
//        return "user";
//    }
//    @RequestMapping("/admin")
//    public String admin()
//    {
//        return "admin";
//    }
}

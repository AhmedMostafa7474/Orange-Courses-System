package com.example.Spring1.Controller;

import com.example.Spring1.Model.Adminn;
import com.example.Spring1.Model.Enroll;
import com.example.Spring1.Security.JwtUtil;
import com.example.Spring1.Service.EnrollService;
import com.example.Spring1.Model.MyUserDetails;
import com.example.Spring1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class EnrollController {

    @Autowired
    EnrollService enrollService;

    @Autowired
    JwtUtil jwtUtils;

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/enroll")
    public ResponseEntity<HashMap<String, String>> enrollcourse(@RequestParam  int course_id,@RequestHeader String Authorization) {
        String Header[] = Authorization.split(" ");
        String username = jwtUtils.extractUsername(Header[1]);
        MyUserDetails userDetails = userService.loadUserByUsername(username);
        String userID=userDetails.getId();
        HashMap<String, String> map= new HashMap<>();
        if(enrollService.enrollcourse(userID,course_id))
        {
            map.put("message","success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else
        {
            map.put("message","failed");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/viewStatus")
    public ResponseEntity<HashMap<String, String>> viewStatus(@RequestParam  int course_id,@RequestHeader String Authorization)
    {
        String Header[] = Authorization.split(" ");
        String username = jwtUtils.extractUsername(Header[1]);
        MyUserDetails userDetails = userService.loadUserByUsername(username);
        String userID=userDetails.getId();
        HashMap<String, String> map= new HashMap<>();

        if(enrollService.viewStatus(userID,course_id).isEmpty())
        {
            map.put("status","failed");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        else
        {
            map.put("status",enrollService.viewStatus(userID,course_id));
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam int id,@RequestParam String message, @RequestHeader String Authorization)
    {
        enrollService.updateStatus(id,message);
        return "Updated";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/SendCode")
    public String sendcode(@RequestParam int id, @RequestHeader String Authorization) {

        if(enrollService.sendcode(id))
        {
            return "Sent";
        }
        else
            return "Failed";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/GetEnrolls")
    public ResponseEntity<HashMap<String, List<Enroll>>>GetAdmins() {
        HashMap<String, List<Enroll>> map= new HashMap<>();
        if(enrollService.GetEnrolls()!=null)
        {
            map.put("message",enrollService.GetEnrolls());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else
        {
            map.put("message",null);
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }
}

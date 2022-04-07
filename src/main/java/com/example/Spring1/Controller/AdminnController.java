package com.example.Spring1.Controller;

import com.example.Spring1.Model.Adminn;
import com.example.Spring1.Service.AdminnServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class AdminnController {
    @Autowired
    AdminnServices adminnServices;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addAdmin")
    public ResponseEntity<HashMap<String, String>> AddAdmin(@RequestBody Adminn adminn, @RequestHeader String Authorization ) {
        HashMap<String, String> map= new HashMap<>();
        if(adminnServices.AddAdmin(adminn))
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
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteAdmin")
    public ResponseEntity<HashMap<String, String>> deleteAdmin(@RequestParam int admin_id, @RequestHeader String Authorization ) {
        HashMap<String, String> map= new HashMap<>();
        if(adminnServices.deleteAdmin(admin_id))
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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/GetAdmins")
    public ResponseEntity<HashMap<String, List<Adminn>>>GetAdmins() {
        HashMap<String, List<Adminn>> map= new HashMap<>();
        if(adminnServices.GetAdmins()!=null)
        {
            map.put("message",adminnServices.GetAdmins());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else
        {
            map.put("message",null);
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }
}

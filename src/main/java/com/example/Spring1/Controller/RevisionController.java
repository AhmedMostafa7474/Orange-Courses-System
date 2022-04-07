package com.example.Spring1.Controller;

import com.example.Spring1.Model.Question;
import com.example.Spring1.Model.Revision;
import com.example.Spring1.Security.JwtUtil;
import com.example.Spring1.Model.MyUserDetails;
import com.example.Spring1.Service.RevisionServices;
import com.example.Spring1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class RevisionController {
    @Autowired
    JwtUtil jwtUtils;

    @Autowired
    UserService userService;

    @Autowired
    RevisionServices revisionServices;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/AddRevision")
    public ResponseEntity<HashMap<String, String>> addRevision(@RequestParam int exam_id, @RequestBody List<String> answers, @RequestHeader String Authorization) {
        String Header[] = Authorization.split(" ");
        String username = jwtUtils.extractUsername(Header[1]);
        MyUserDetails userDetails = userService.loadUserByUsername(username);
        String userID = userDetails.getId();
        HashMap<String, String> map = new HashMap<>();
        if (revisionServices.addRevision(exam_id, Integer.parseInt(userID), answers)) {
            map.put("message", "success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("message", "failed");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/viewDegree")
    public ResponseEntity<HashMap<String, String>> viewExamdegree(@RequestParam int exam_id, @RequestParam int user_id,@RequestHeader String Authorization) {
        HashMap<String, String> map = new HashMap<>();
        double result = revisionServices.viewExamdegree(exam_id, user_id);
        if (result != -1) {
            map.put("Degree", String.valueOf(result));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("message", null);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/GetRevisions")
    public ResponseEntity<HashMap<String, List<Revision>>> GetQuestions() {
        HashMap<String, List<Revision>> map= new HashMap<>();
        if(revisionServices.GetRevisions()!=null)
        {
            map.put("message",revisionServices.GetRevisions());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else
        {
            map.put("message",null);
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }
}

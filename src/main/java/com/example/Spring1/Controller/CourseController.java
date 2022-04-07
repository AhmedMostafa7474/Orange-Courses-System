package com.example.Spring1.Controller;

import com.example.Spring1.Model.Course;
import com.example.Spring1.Service.CourseServices;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseServices courseServices;


    @GetMapping("/GetCourses")
    public ResponseEntity<HashMap<String, List<Course>>> GetAllCourses() {
        HashMap<String, List<Course>> map= new HashMap<>();

        if(courseServices.GetAllCourses()!=null)
        {
            map.put("Courses",courseServices.GetAllCourses());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("Courses",null);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @PostMapping("/AddCourse")
    public ResponseEntity<HashMap<String, String>> AddCourses(@RequestBody Course course, @RequestHeader String Authorization) {
        HashMap<String, String> map= new HashMap<>();

        if(courseServices.AddCourse(course))
        {
            map.put("Courses", "Success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("Courses","Failed");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @DeleteMapping("/deleteCourse")
    public ResponseEntity<HashMap<String, String>> deleteQuestion(@RequestParam int course_id,@RequestHeader String Authorization) {
        HashMap<String, String> map= new HashMap<>();

        if(courseServices.DeleteCourse(course_id))
        {
            map.put("Courses", "Success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("Courses","Failed");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
}

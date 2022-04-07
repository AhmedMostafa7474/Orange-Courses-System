package com.example.Spring1.Controller;

import com.example.Spring1.Model.Enroll;
import com.example.Spring1.Model.Exam;
import com.example.Spring1.Model.Question;
import com.example.Spring1.Repo.CourseRepo;
import com.example.Spring1.Repo.QuestionRepo;
import com.example.Spring1.Security.JwtUtil;
import com.example.Spring1.Service.ExamServices;
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
public class ExamController {

    @Autowired
    ExamServices examServices;

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    JwtUtil jwtUtils;

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @PostMapping("/AddExam")
    public ResponseEntity<HashMap<String, String>> addExam(@RequestParam int course_id, @RequestBody List<Integer> questions, @RequestHeader String Authorization ) {

        HashMap<String, String> map= new HashMap<>();
        if(examServices.addExam(course_id,questions))
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
    @GetMapping("/GetExam")
    public ResponseEntity<HashMap<String, List<Question>>> GetExam(@RequestParam int exam_id, @RequestHeader String Authorization ) {
        String Header[] = Authorization.split(" ");
        String username = jwtUtils.extractUsername(Header[1]);
        MyUserDetails userDetails = userService.loadUserByUsername(username);
        String userID=userDetails.getId();
        HashMap<String, List<Question>> map= new HashMap<>();
        Exam exam=examServices.GetExam(exam_id,Integer.parseInt(userID));
        if(exam!=null)
        {
            map.put("message",exam.getQuestionList());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else
        {
            map.put("message",null);
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @DeleteMapping("/deleteExam")
    public ResponseEntity<HashMap<String, String>> deleteQuestion(@RequestParam int exam_id,@RequestHeader String Authorization) {
        HashMap<String, String> map= new HashMap<>();

        if(examServices.DeleteExam(exam_id))
        {
            map.put("message", "Success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("message","Failed");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/GetExams")
    public ResponseEntity<HashMap<String, List<Exam>>>GetAdmins() {
        HashMap<String, List<Exam>> map= new HashMap<>();
        if(examServices.GetExams()!=null)
        {
            map.put("message",examServices.GetExams());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else
        {
            map.put("message",null);
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }
}

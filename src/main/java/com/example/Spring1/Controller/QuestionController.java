package com.example.Spring1.Controller;

import com.example.Spring1.Model.Exam;
import com.example.Spring1.Model.Question;
import com.example.Spring1.Repo.QuestionRepo;
import com.example.Spring1.Service.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    QuestionServices questionServices;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @PostMapping("/addQuestion")
    public ResponseEntity<HashMap<String, String>> addQuestion(@RequestBody Question question,@RequestHeader String Authorization) {
        HashMap<String, String> map= new HashMap<>();

        if(questionServices.addQuestion(question))
        {
            map.put("Message", "Success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("Message","Failed");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @DeleteMapping("/deleteQuestion")
    public ResponseEntity<HashMap<String, String>> deleteQuestion(@RequestParam int question_id,@RequestHeader String Authorization) {
        HashMap<String, String> map= new HashMap<>();

        if(questionServices.deleteQuestion(question_id))
        {
            map.put("Message", "Success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("Message","Failed");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/GetQuestions")
    public ResponseEntity<HashMap<String, List<Question>>> GetQuestions() {
        HashMap<String, List<Question>> map= new HashMap<>();
        if(questionServices.GetQuestions()!=null)
        {
            map.put("message",questionServices.GetQuestions());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else
        {
            map.put("message",null);
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }
}

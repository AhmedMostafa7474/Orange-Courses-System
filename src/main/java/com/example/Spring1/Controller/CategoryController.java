package com.example.Spring1.Controller;

import com.example.Spring1.Model.Adminn;
import com.example.Spring1.Model.Category;
import com.example.Spring1.Service.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryServices categoryServices;
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @PostMapping("/AddCategory")
    public ResponseEntity<HashMap<String, String>> AddCategory(@RequestBody Category category,@RequestHeader String Authorization) {
        HashMap<String, String> map = new HashMap<>();
        if (categoryServices.AddCategory(category)) {
            map.put("message", "success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("message", "failed");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/GetCategories")
    public ResponseEntity<HashMap<String, List<Category>>>GetAdmins() {
        HashMap<String, List<Category>> map= new HashMap<>();
        if(categoryServices.Categorys()!=null)
        {
            map.put("message",categoryServices.Categorys());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else
        {
            map.put("message",null);
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
    }
}

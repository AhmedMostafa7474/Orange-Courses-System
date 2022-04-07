package com.example.Spring1.Repo;

import com.example.Spring1.Model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends CrudRepository<Course,Integer> {
}

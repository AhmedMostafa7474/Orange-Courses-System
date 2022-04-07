package com.example.Spring1.Repo;

import com.example.Spring1.Model.Course;
import com.example.Spring1.Model.Exam;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExamRepo extends CrudRepository<Exam,Integer> {

    Iterable<Exam> findAllByCourse(Course course);
    Iterable<Exam> deleteAllByCourse(Course course);
    Optional<Exam> findByCode(int code);
}

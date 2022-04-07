package com.example.Spring1.Service;

import com.example.Spring1.Interfaces.ICourseServices;
import com.example.Spring1.Model.Course;
import com.example.Spring1.Model.Exam;
import com.example.Spring1.Model.ExamQuestion;
import com.example.Spring1.Repo.CourseRepo;
import com.example.Spring1.Repo.ExamQuestionRepo;
import com.example.Spring1.Repo.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServices implements ICourseServices {
    @Autowired
    CourseRepo courseRepo;

    @Autowired
    ExamRepo examRepo;

    @Autowired
    ExamQuestionRepo examQuestionRepo;

    @Override
    public List<Course> GetAllCourses() {
        List<Course>courseList= (List<Course>) courseRepo.findAll();
        courseList.forEach(course -> course.setExams(new ArrayList<>()));
        return courseList;
    }

    @Override
    public boolean AddCourse(Course course) {
       try {
           course.setCreate_at(new Timestamp(System.currentTimeMillis()));
           courseRepo.save(course);
           return true;
       }catch (Exception e) {
           return false;
       }
    }

    @Override
    public boolean DeleteCourse(int course_id) {
        try {
           Course course= courseRepo.findById(course_id).get();
           List<Exam>exams= (List<Exam>) examRepo.findAllByCourse(course);
           for (int i=0;i<exams.size();i++) {
               examQuestionRepo.deleteByExam(exams.get(i));
           }
            examRepo.deleteAllByCourse(course);
            courseRepo.deleteById(course_id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}

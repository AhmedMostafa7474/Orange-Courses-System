package com.example.Spring1.Interfaces;

import com.example.Spring1.Model.Course;

import java.util.List;

public interface ICourseServices {
    public List<Course>GetAllCourses();
    public boolean AddCourse(Course course);
    public boolean DeleteCourse(int course_id);
}

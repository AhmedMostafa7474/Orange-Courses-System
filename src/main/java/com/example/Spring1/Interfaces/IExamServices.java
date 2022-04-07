package com.example.Spring1.Interfaces;

import com.example.Spring1.Model.Course;
import com.example.Spring1.Model.Enroll;
import com.example.Spring1.Model.Exam;
import com.example.Spring1.Model.Question;

import java.util.List;

public interface IExamServices {
    public boolean addExam(int course_id, List<Integer> questionList);
    public Exam GetExam(int exam_id,int user_id);
    public boolean DeleteExam(int exam_id);
    public List<Exam> GetExams();
}

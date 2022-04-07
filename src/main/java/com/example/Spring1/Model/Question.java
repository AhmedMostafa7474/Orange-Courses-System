package com.example.Spring1.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String question_content;
    String question_answer;

    @ManyToMany
    @JoinTable(name = "exam_question", joinColumns = { @JoinColumn(name = "exam_id") },
            inverseJoinColumns = { @JoinColumn(name = "question_id") })
    List<Exam> exams=new ArrayList<>();

    String wrong_first;
    String wrong_second;
    String wrong_third;

    public Question(String question_content, String question_answer, String wrong_first, String wrong_second, String wrong_third) {
        this.question_content = question_content;
        this.question_answer = question_answer;
        this.wrong_first = wrong_first;
        this.wrong_second = wrong_second;
        this.wrong_third = wrong_third;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }


    public List<Exam> getExam() {
        return exams;
    }

    public void setExam(List<Exam> exam) {
        this.exams = exam;
    }

    public String getWrong_first() {
        return wrong_first;
    }

    public void setWrong_first(String wrong_first) {
        this.wrong_first = wrong_first;
    }

    public String getWrong_second() {
        return wrong_second;
    }

    public void setWrong_second(String wrong_second) {
        this.wrong_second = wrong_second;
    }

    public String getWrong_third() {
        return wrong_third;
    }

    public void setWrong_third(String wrong_third) {
        this.wrong_third = wrong_third;
    }
}

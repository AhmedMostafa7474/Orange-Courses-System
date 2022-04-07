package com.example.Spring1.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "exam_question")
public class ExamQuestion {

    @ManyToOne
    @JoinColumn(name="exam_id",nullable = false)
    Exam exam;

    @ManyToOne
    @JoinColumn(name="question_id",nullable = false)
    Question question;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    public ExamQuestion() {
    }

    public ExamQuestion(Exam exam, Question question) {
        this.exam = exam;
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

package com.example.Spring1.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    double student_degree;
    double total_right_degree;
    double total_wrong_degree;

    @OneToOne
    @JoinColumn(name="exam_id", nullable=false)
    Exam exam;

    @OneToOne
    @JoinColumn(name="student_id", nullable=false)
    User user;

    public Revision(double student_degree, double total_right_degree, double total_wrong_degree, Exam exam, User user) {
        this.student_degree = student_degree;
        this.total_right_degree = total_right_degree;
        this.total_wrong_degree = total_wrong_degree;
        this.exam = exam;
        this.user = user;
    }
    public Revision()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStudent_degree() {
        return student_degree;
    }

    public void setStudent_degree(double student_degree) {
        this.student_degree = student_degree;
    }

    public double getTotal_right_degree() {
        return total_right_degree;
    }

    public void setTotal_right_degree(double total_right_degree) {
        this.total_right_degree = total_right_degree;
    }

    public double getTotal_wrong_degree() {
        return total_wrong_degree;
    }

    public void setTotal_wrong_degree(double total_wrong_degree) {
        this.total_wrong_degree = total_wrong_degree;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

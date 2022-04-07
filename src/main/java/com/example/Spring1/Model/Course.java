package com.example.Spring1.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    int id;

    String course_name;
    String course_level;
    Timestamp create_at;
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    @JsonBackReference
    Category category;

    @OneToMany(mappedBy = "course")
    List<Exam> exams=new ArrayList<>();

    public Course(int id,String course_name, String course_level, Category category) {
        this.id=id;
        this.course_name = course_name;
        this.course_level = course_level;
        this.create_at = new Timestamp(System.currentTimeMillis());
        this.category = category;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_level() {
        return course_level;
    }

    public void setCourse_level(String course_level) {
        this.course_level = course_level;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}

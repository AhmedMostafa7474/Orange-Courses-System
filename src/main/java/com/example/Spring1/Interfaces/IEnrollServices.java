package com.example.Spring1.Interfaces;

import com.example.Spring1.Model.Enroll;

import java.util.List;

public interface IEnrollServices {
    public boolean enrollcourse(String user_id,int course_id);
    public String viewStatus(String user_id,int course_id);
    public boolean updateStatus(int id,String message);
    public boolean sendcode(int id);
    public List<Enroll> GetEnrolls();
}

package com.example.Spring1.Interfaces;

import com.example.Spring1.Model.Revision;

import java.util.List;

public interface IRevisionServices {
    public boolean addRevision(int exam_id, int user_id, List<String> answers);
    public double viewExamdegree(int exam_id, int user_id);
    public List<Revision>GetRevisions();
}

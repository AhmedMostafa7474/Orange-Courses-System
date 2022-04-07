package com.example.Spring1.Interfaces;

import com.example.Spring1.Model.Question;

import java.util.List;

public interface IQuestionServices {
    public boolean addQuestion(Question question);
    public boolean deleteQuestion(int question_id);
    public List<Question>GetQuestions();

}

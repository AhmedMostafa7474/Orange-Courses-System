package com.example.Spring1.Service;

import com.example.Spring1.Interfaces.IQuestionServices;
import com.example.Spring1.Model.Exam;
import com.example.Spring1.Model.Question;
import com.example.Spring1.Repo.ExamQuestionRepo;
import com.example.Spring1.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServices implements IQuestionServices {

    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    ExamQuestionRepo examQuestionRepo;
    @Override
    public boolean addQuestion(Question question) {
        try {
            questionRepo.save(question);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean deleteQuestion(int question_id) {
        try {
            Question question=questionRepo.findById(question_id).get();
            examQuestionRepo.deleteByQuestion(question);
            questionRepo.deleteById(question_id);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<Question> GetQuestions() {
        try {
            List<Question>questions= (List<Question>) questionRepo.findAll();
            return questions;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

package com.example.Spring1.Repo;

import com.example.Spring1.Model.Exam;
import com.example.Spring1.Model.ExamQuestion;
import com.example.Spring1.Model.Question;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ExamQuestionRepo extends CrudRepository<ExamQuestion,Integer> {
    Iterable<ExamQuestion>findAllByExam(Exam exam);
    @Transactional
    Integer deleteByExam(Exam exam);
    @Transactional
    Integer deleteByQuestion(Question question);
}

package com.example.Spring1.Service;

import com.example.Spring1.Interfaces.IExamServices;
import com.example.Spring1.Model.*;
import com.example.Spring1.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServices implements IExamServices {
    @Autowired
    ExamRepo examRepo;
    @Autowired
    ExamQuestionRepo examQuestionRepo;
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    RevisionRepo revisionRepo;

    @Override
    public boolean addExam(int course_id, List<Integer> questionList) {
        try {
            List<Question>questions=new ArrayList<>();

            for (int i=0;i<questionList.size();i++)
            {
                Question question= questionRepo.findById(questionList.get(i)).get();
                System.out.println(question.getQuestion_answer()+" yeeeeeeeeeee");
                questions.add(question);
            }
            Course course=courseRepo.findById(course_id).get();
            Exam exam=new Exam(course);
            exam.setQuestionList(questions);
            int min = 100000;
            int max = 999999;
            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            exam.setCode(random_int);
            exam.setId(random_int);
            examRepo.save(exam);
            for(int i=0;i<questions.size();i++)
            {
                ExamQuestion examQuestion=new ExamQuestion(exam,questions.get(i));
                System.out.println(examQuestion.getExam().getId());
                examQuestionRepo.save(examQuestion);
            }
            return true;

        }catch (Exception e){
            return false;
        }

    }

    @Override
    public Exam GetExam(int code, int user_id) {
       try {
           List<Question>questionList=new ArrayList<>();
           Exam exam = examRepo.findByCode(code).get();
           System.out.println(exam.getId()+"sss");
           Exam exam1=exam;
           List<ExamQuestion> examQuestion= (List<ExamQuestion>) examQuestionRepo.findAllByExam(exam);
           for(int i=0;i<examQuestion.size();i++)
           {
               System.out.println("yeeeeeeeeeees");
               questionList.add(examQuestion.get(i).getQuestion());
           }
           exam.setQuestionList(questionList);
           int min = 100000;
           int max = 999999;
           int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
           exam1.setCode(random_int);
           examRepo.save(exam1);
           System.out.println(exam.getQuestionList().get(0).getQuestion_answer());
           return exam;
       }catch (Exception e)
       {
           return null;
       }
    }

    @Override
    public boolean DeleteExam(int exam_id) {
        try {
            Exam exam=examRepo.findById(exam_id).get();
            examQuestionRepo.deleteByExam(exam);
            revisionRepo.deleteByExam(exam);
            examRepo.deleteById(exam_id);
            System.out.println("no");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<Exam> GetExams() {
        try {
            List<Exam>exams= (List<Exam>) examRepo.findAll();
            exams.forEach(exam -> exam.getCourse().setExams(null));
            return exams;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

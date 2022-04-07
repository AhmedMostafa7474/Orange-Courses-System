package com.example.Spring1.Service;

import com.example.Spring1.Interfaces.IRevisionServices;
import com.example.Spring1.Model.*;
import com.example.Spring1.Repo.ExamQuestionRepo;
import com.example.Spring1.Repo.ExamRepo;
import com.example.Spring1.Repo.RevisionRepo;
import com.example.Spring1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RevisionServices implements IRevisionServices {
    @Autowired
    ExamRepo examRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RevisionRepo revisionRepo;
    @Autowired
    ExamQuestionRepo examQuestionRepo;
    @Override
    public boolean addRevision(int exam_id, int user_id, List<String> answers) {
        try {
            Exam exam=examRepo.findById(exam_id).get();
            List<ExamQuestion>examQuestions= (List<ExamQuestion>) examQuestionRepo.findAllByExam(exam);
            List<Question>questions=new ArrayList<>();

            for (int i=0;i<examQuestions.size();i++)
            {

                Question question= examQuestions.get(i).getQuestion();
                System.out.println(examQuestions.get(i).getQuestion().getQuestion_content());
                questions.add(question);
            }
            User user=userRepo.findById(user_id).get();
            System.out.println(user.getId()+" sssssss");
            double right=0.0;
            double wrong=0.0;
            double degree=0.0;
            for(int i=0;i<questions.size();i++)
            {
                if(answers.get(i).equals(questions.get(i).getQuestion_answer()))
                {
                    right=right+1.0;
                }
                else
                {
                    wrong=wrong+1.0;
                }
            }
            degree=(right/(right+wrong))*100.0;
            System.out.println(degree);
            Revision revision=new Revision(degree,right,wrong,exam,user);
            revisionRepo.save(revision);
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public double viewExamdegree(int exam_id, int user_id) {
        try {
            Exam exam=examRepo.findById(exam_id).get();
            User user=userRepo.findById(user_id).get();
            System.out.println(user.getId()+" "+exam.getId());
            Revision revision=  revisionRepo.findByUserAndExam(user,exam).get();
            System.out.println(revision.getStudent_degree());
            return revision.getStudent_degree();
        }catch (Exception e)
        {
            return -1;
        }

    }

    @Override
    public List<Revision> GetRevisions() {
        try {
            List<Revision>revisions= (List<Revision>) revisionRepo.findAll();
            revisions.forEach(revision -> revision.getExam().getCourse().setExams(null));
            return revisions;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

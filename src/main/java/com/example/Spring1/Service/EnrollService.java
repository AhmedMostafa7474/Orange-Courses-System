package com.example.Spring1.Service;

import com.example.Spring1.Interfaces.IEnrollServices;
import com.example.Spring1.Model.*;
import com.example.Spring1.Repo.CourseRepo;
import com.example.Spring1.Repo.EnrollRepo;
import com.example.Spring1.Repo.ExamRepo;
import com.example.Spring1.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.Random;

@Service
public class EnrollService extends SimpleMailMessage  implements IEnrollServices {


    @Autowired
    UserRepo userRepo;

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    EnrollRepo enrollRepo;

    @Autowired
    ExamRepo examRepo;

    @Override
    public boolean enrollcourse(String user_id,int course_id) {
        User u=userRepo.findById(Integer.parseInt(user_id)).get();
        Course c=courseRepo.findById(course_id).get();
        List<Enroll>enrollList= (List<Enroll>) enrollRepo.findAllByUser(u);

        for(int i=0;i<enrollList.size();i++)
        {
            if(enrollList.get(i).getStatus()!="Completed"||enrollList.get(i).getStatus()!="Rejected")
            {
                return false;
            }
        }
        Enroll enroll=new Enroll();
        enroll.setCourse(c);
        enroll.setUser(u);
        enrollRepo.save(enroll);
        return true;
    }

    @Override
    public String viewStatus(String user_id, int course_id) {
        User u=userRepo.findById(Integer.parseInt(user_id)).get();
        Course c=courseRepo.findById(course_id).get();
        List<Enroll>enrollList= (List<Enroll>) enrollRepo.findAllByUser(u);
        for(int i=0;i<enrollList.size();i++)
        {
            if(enrollList.get(i).getCourse().getId()==course_id)
            {
                return enrollList.get(i).getStatus();
            }
        }
        return "";
    }
    @Override
    public boolean updateStatus(int id,String message)
    {
        try {
            User user=userRepo.findById(id).get();
            List<Enroll>enrolls= (List<Enroll>) enrollRepo.findAllByUser(user);
            for(int i=0;i<enrolls.size();i++)
            {
                if(enrolls.get(i).getStatus()!="Completed"||enrolls.get(i).getStatus()!="Rejected")
                {
                    enrolls.get(i).setStatus(message);
                    enrollRepo.save(enrolls.get(i));

                }
            }
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.yahoo.com");
        mailSender.setPort(587);

        mailSender.setUsername("conancodo40@yahoo.com");
        mailSender.setPassword("kqluwlryxmhefmcb");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Override
    public boolean sendcode(int id) {

       try {
           User user=userRepo.findById(id).get();
           System.out.println("user "+user.getStudent_name());
           List<Enroll> enrolls= (List<Enroll>) enrollRepo.findAllByUser(user);
           System.out.println("enroll "+enrolls.get(0).getId());
           Course course = null;
           for(int i=0;i<enrolls.size();i++)
           {
               if(enrolls.get(i).getStatus().equals("Waiting"))
               {
                    course=enrolls.get(i).getCourse();
               }
           }
           System.out.println("course "+course.getId());
           List<Exam>exams= (List<Exam>) examRepo.findAllByCourse(course);
           System.out.println("exam "+exams.get(0).getId());
           Random rand = new Random();
           Exam randomexam = exams.get(rand.nextInt(exams.size()));
           JavaMailSender emailSender=getJavaMailSender() ;
           SimpleMailMessage message = new SimpleMailMessage();
           message.setFrom("conancodo40@yahoo.com");
           message.setTo(user.getEmail());
           message.setSubject("Code");
           message.setText("code is "+randomexam.getCode());
           emailSender.send(message);
           return true;
       }catch (Exception e)
       {
           return false;
       }

    }

    @Override
    public List<Enroll> GetEnrolls() {
            try {
                List<Enroll>enrolls= (List<Enroll>) enrollRepo.findAll();
                enrolls.forEach(enroll -> enroll.getCourse().setExams(null));
                return enrolls;
            }
            catch (Exception e)
            {
                return null;
            }
    }
}

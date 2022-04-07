package com.example.Spring1.Repo;

import com.example.Spring1.Model.Exam;
import com.example.Spring1.Model.Revision;
import com.example.Spring1.Model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface RevisionRepo extends CrudRepository<Revision,Integer> {
    Optional<Revision> findByUserAndExam(User user, Exam exam);
    Optional<Revision> findByUser(User user);
    @Transactional
    Integer deleteByExam(Exam exam);
}

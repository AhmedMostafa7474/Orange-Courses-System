package com.example.Spring1.Repo;

import com.example.Spring1.Model.Enroll;
import com.example.Spring1.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollRepo extends CrudRepository<Enroll,Integer> {
    Iterable<Enroll> findAllByUser(User user);
}

package com.example.Spring1.Repo;

import com.example.Spring1.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo  extends CrudRepository<User,Integer> {
    Optional<User> findByStudentName(String name);
    Optional<User> findByEmail(String name);
}

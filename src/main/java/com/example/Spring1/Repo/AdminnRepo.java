package com.example.Spring1.Repo;

import com.example.Spring1.Model.Adminn;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminnRepo extends CrudRepository<Adminn,Integer> {
    Optional<Adminn> findByUsername(String name);
}

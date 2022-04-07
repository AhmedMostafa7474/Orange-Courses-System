package com.example.Spring1.Repo;

import com.example.Spring1.Model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepo extends CrudRepository<Question,Integer> {
}

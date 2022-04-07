package com.example.Spring1.Repo;

import com.example.Spring1.Model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category,Integer> {
}

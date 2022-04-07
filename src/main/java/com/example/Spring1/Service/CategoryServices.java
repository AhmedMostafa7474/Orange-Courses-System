package com.example.Spring1.Service;

import com.example.Spring1.Interfaces.ICategoryServices;
import com.example.Spring1.Model.Adminn;
import com.example.Spring1.Model.Category;
import com.example.Spring1.Repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CategoryServices implements ICategoryServices {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public boolean AddCategory(Category category) {
        try {
            category.setCreate_at(new Timestamp(System.currentTimeMillis()));
            categoryRepo.save(category);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<Category> Categorys() {
        try {
            List<Category>categories= (List<Category>) categoryRepo.findAll();
            return categories;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

package com.example.Spring1.Interfaces;

import com.example.Spring1.Model.Adminn;
import com.example.Spring1.Model.Category;

import java.util.List;

public interface ICategoryServices {
    public boolean AddCategory(Category category);
    public List<Category> Categorys();
}

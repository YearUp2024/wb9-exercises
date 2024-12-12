package com.pluralsight.NorthwindTradersAPI.dao.interfaces;

import com.pluralsight.NorthwindTradersAPI.Models.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAll();
    Category findByCategoryId(int id);
    boolean insert(Category category);
    boolean updateCategory(int id, Category category);
    boolean deleteCategory(int id);
}

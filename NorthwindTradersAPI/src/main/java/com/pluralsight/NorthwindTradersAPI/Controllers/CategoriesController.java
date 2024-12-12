package com.pluralsight.NorthwindTradersAPI.Controllers;

import com.pluralsight.NorthwindTradersAPI.Models.Category;
import com.pluralsight.NorthwindTradersAPI.dao.interfaces.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private CategoryDao categoryDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping("/")
    public List<Category> getCategories(){
        return categoryDao.getAll();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id){
        return categoryDao.findByCategoryId(id);
    }

    @PostMapping("/")
    public Boolean addCategory(@RequestBody Category category){
        return categoryDao.insert(category);
    }

    @PutMapping("/{id}")
    public Boolean updateCategory(@PathVariable int id, @RequestBody Category category){
        return categoryDao.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCategory(@PathVariable int id){
        System.out.println( "Update Category: " + id);
        return categoryDao.deleteCategory(id);
    }
}

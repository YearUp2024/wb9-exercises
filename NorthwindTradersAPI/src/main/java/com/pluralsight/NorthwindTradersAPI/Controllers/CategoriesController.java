package com.pluralsight.NorthwindTradersAPI.Controllers;

import com.pluralsight.NorthwindTradersAPI.Models.Category;
import com.pluralsight.NorthwindTradersAPI.dao.interfaces.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController {
    private CategoryDao categoryDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @RequestMapping(path="/categories", method = RequestMethod.GET)
    public List<Category> getCategories(){
        return categoryDao.getAll();
    }

    @RequestMapping(path="/categories/{id}", method= RequestMethod.GET)
    public Category getCategory(@PathVariable int id){
        return categoryDao.findByCategoryId(id);
    }
}

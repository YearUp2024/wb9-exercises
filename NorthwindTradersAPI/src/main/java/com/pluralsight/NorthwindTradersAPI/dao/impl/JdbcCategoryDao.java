package com.pluralsight.NorthwindTradersAPI.dao.impl;

import com.pluralsight.NorthwindTradersAPI.Models.Category;
import com.pluralsight.NorthwindTradersAPI.dao.interfaces.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCategoryDao implements CategoryDao {
    private DataSource dataSource;

    @Autowired
    public JdbcCategoryDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();

        String sql = "Select CategoryId, CategoryName FROM categories;";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
        ){
            while(resultSet.next()){
                int catId = resultSet.getInt(1);
                String catName = resultSet.getString(2);
                categories.add(new Category(catId, catName));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findByCategoryId(int id) {
        Category category;

        String sql = "SELECT CategoryId, CategoryName FROM categories WHERE CategoryId = ?;";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int catId = resultSet.getInt(1);
                    String catName = resultSet.getString(2);
                    return new Category(catId, catName);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

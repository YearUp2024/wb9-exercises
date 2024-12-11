package com.pluralsight.NorthwindTradersAPI.dao.impl;

import com.pluralsight.NorthwindTradersAPI.Models.Product;
import com.pluralsight.NorthwindTradersAPI.dao.interfaces.ProductDao;
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
public class JdbcProductDao implements ProductDao {
    private final DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT ProductID, ProductName, CategoryId, UnitPrice FROM northwind.Products";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                int productId = resultSet.getInt(1);
                String productName = resultSet.getString(2);
                int categoryId = resultSet.getInt(3);
                double unitPrice = resultSet.getDouble(4);
                products.add(new Product(productId, productName, categoryId, unitPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getByProductId(int id) {
        Product product;
        String sql = "SELECT ProductID, ProductName, CategoryId, UnitPrice FROM northwind.Products WHERE ProductID = ?";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int productId = resultSet.getInt(1);
                    String productName = resultSet.getString(2);
                    int productCategoryId = resultSet.getInt(3);
                    double unitPrice = resultSet.getDouble(4);

                    return new Product(productId, productName, productCategoryId, unitPrice);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

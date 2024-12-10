package com.pluralsight.NorthwindTradersAPI.dao.impl;

import com.pluralsight.NorthwindTradersAPI.Models.Product;
import com.pluralsight.NorthwindTradersAPI.dao.interfaces.ProductDao;

import java.util.List;

public class JdbcProductDao implements ProductDao {


    @Override
    public List<Product> getAll() {
        return List.of();
    }

    @Override
    public Product getByProductId(int id) {
        return null;
    }
}

package com.pluralsight.NorthwindTradersSpringBoot;

import java.util.List;

public interface ProductDAO {
    void add(Product product);
    List<Product> getAll();
}

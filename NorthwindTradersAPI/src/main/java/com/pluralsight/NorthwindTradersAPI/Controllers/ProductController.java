package com.pluralsight.NorthwindTradersAPI.Controllers;

import com.pluralsight.NorthwindTradersAPI.Models.Product;
import com.pluralsight.NorthwindTradersAPI.dao.interfaces.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private ProductDao productDao;

    @Autowired
    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    private List<Product> getProducts(){
        return productDao.getAll();
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id){
        if(id == 1){
            return new Product(1, "Banana", 1, 5.99d);
        }else if(id == 2){
            return new Product(2, "Apple", 1, 15.99d);
        }
        return null;
    }
}

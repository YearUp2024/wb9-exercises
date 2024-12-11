package com.pluralsight.NorthwindTradersAPI.Controllers;

import com.pluralsight.NorthwindTradersAPI.Models.Product;
import com.pluralsight.NorthwindTradersAPI.dao.interfaces.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductDao productDao;

    @Autowired
    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/all")
    private List<Product> getProducts(){
        return productDao.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id){
        return productDao.getByProductId(id);
    }

    @PostMapping("/add")
    public Boolean addProduct(@RequestBody Product product) {
        return productDao.insert(product);
    }
}

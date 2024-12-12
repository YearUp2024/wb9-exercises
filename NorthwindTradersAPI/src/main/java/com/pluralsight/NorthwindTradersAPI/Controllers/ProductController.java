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

    @GetMapping("/")
    private List<Product> getProducts(){
        return productDao.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id){
        return productDao.getByProductId(id);
    }

    @PostMapping("/")
    public Boolean addProduct(@RequestBody Product product) {
        return productDao.insert(product);
    }

    @PutMapping("/{id}")
    public Boolean updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productDao.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable int id){
        System.out.println("Update " + id);
        return productDao.deleteProduct(id);
    }
}

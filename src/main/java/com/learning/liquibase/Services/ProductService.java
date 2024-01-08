package com.learning.liquibase.Services;

import com.learning.liquibase.Models.Product;
import com.learning.liquibase.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    public Product create(Product product){
        Product res= productRepo.save(product);
        return res;
    }
}

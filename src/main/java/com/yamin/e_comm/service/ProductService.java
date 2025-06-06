package com.yamin.e_comm.service;

import com.yamin.e_comm.model.Product;
import com.yamin.e_comm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    // TODO: handle the error with proper status code
    public Product getProductById(int prodId) {
        return repo.findById(prodId).orElse(null);
    }
}

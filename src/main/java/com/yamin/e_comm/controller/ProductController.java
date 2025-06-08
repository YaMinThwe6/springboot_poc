package com.yamin.e_comm.controller;

import com.yamin.e_comm.dto.ApiResponse;
import com.yamin.e_comm.model.Product;
import com.yamin.e_comm.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;

    @Autowired
    private ApiResponse apiResponse;

    @GetMapping("/products")
    public ResponseEntity<ApiResponse> getAllProducts() {
        return new ResponseEntity<>(new ApiResponse(true, "Products fetched!", service.getAllProducts()), HttpStatus.OK);
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable int prodId) {
        Product product = service.getProductById(prodId);
        if (product != null) {
            return new ResponseEntity<>(new ApiResponse(true, "Product fetched!", product), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResponse(false, "Product not found!"),HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody Product product) {
        try {
            Product addedProduct = service.addProduct(product);
            logger.info("Product updated successfully! Product Id: " + addedProduct.getId());
            return new ResponseEntity<>(new ApiResponse(true, "Product added successfully!", addedProduct), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, "Something went wrong!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product/image")
    public ResponseEntity<ApiResponse> addImage(@RequestParam MultipartFile image) {
        String imageUrl = service.addImage(image);
        logger.info("Image added successfully!");
        return new ResponseEntity<>(new ApiResponse(true, "Image uploaded successfully!", imageUrl), HttpStatus.OK);
    }

    @PutMapping("/product/{prodId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable int prodId, @RequestBody Product product) {
        try {
            Product updatedProduct = service.updateProduct(prodId, product);
            if (updatedProduct == null) {
                logger.warn("Product not found! Product Id: " + prodId);
                return new ResponseEntity<>(new ApiResponse(false, "Product Not found!"), HttpStatus.NOT_FOUND);
            }
            logger.info("Product Updated successfully!" + updatedProduct.toString());
            return new ResponseEntity<>(new ApiResponse(true, "Product updated successfully!", updatedProduct), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Message: {}, Cause: {}, StackTrace: {}", e.getMessage(), e.getCause(), Arrays.toString(e.getStackTrace()));
            return new ResponseEntity<>(new ApiResponse(false, "Something went wrong!"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int prodId) {
        boolean isDeleted = service.deleteProduct(prodId);
        if (isDeleted) {
            return new ResponseEntity<>(new ApiResponse(true, "Deleted Successfully!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "Product Not Found!"), HttpStatus.NOT_FOUND);
    }
}

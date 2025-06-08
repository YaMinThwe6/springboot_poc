package com.yamin.e_comm.service;

import com.yamin.e_comm.model.Product;
import com.yamin.e_comm.repository.ProductRepository;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.ByteArrayEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repo;

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service.key}")
    private String serviceKey;

    @Value("${supabase.bucket}")
    private String bucketName;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int prodId) {
        return repo.findById(prodId).orElse(null);
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public Product updateProduct(int prodId, Product product) {
        Product productToBeUpdated = repo.findById(prodId).orElse(null);
        if (productToBeUpdated == null) {
            return null;
        }
        productToBeUpdated.setBrand(product.getBrand());
        productToBeUpdated.setName(product.getName());
        productToBeUpdated.setPrice(product.getPrice());
        productToBeUpdated.setCategory(product.getCategory());
        productToBeUpdated.setAvailable(product.isAvailable());
        productToBeUpdated.setDescription(product.getDescription());
        productToBeUpdated.setImage_url(product.getImage_url());
        productToBeUpdated.setReleased_date(product.getReleased_date());
        productToBeUpdated.setQuantity(product.getQuantity());

        repo.save(productToBeUpdated);

        return productToBeUpdated;
    }

    public String addImage(MultipartFile image) {
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        String fileUrl = "";
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(supabaseUrl + "storage/v1/object/" + bucketName + "/" + fileName);
            post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + serviceKey);
            post.setHeader(HttpHeaders.CONTENT_TYPE, image.getContentType());
            try (ByteArrayEntity entity = new ByteArrayEntity(image.getBytes(), ContentType.parse(image.getContentType()))) {
                post.setEntity(entity);
            }

            try (CloseableHttpResponse response = client.execute(post)) {
                if (response.getCode() == 200 || response.getCode() == 201) {
                    fileUrl = supabaseUrl + "/storage/v1/object/public/" + bucketName + "/" + fileName;
                } else {
                    throw new RuntimeException("Upload failed: " + response.getReasonPhrase());
                }
            }
        } catch (IOException e) {
            logger.error("Message: {}, Cause: {}, StackTrace: {}", e.getMessage(), e.getCause(), Arrays.toString(e.getStackTrace()));
            throw new RuntimeException("Image Upload failed");
        }

        return fileUrl;
    }

    public boolean deleteProduct(int prodId) {
        Product productToBeDeleted = repo.findById(prodId).orElse(null);
        if (productToBeDeleted == null) {
            return false;
        }
        String imageToBeDeleted = productToBeDeleted.getImage_url();
        if (imageToBeDeleted != null) {
            String[] parts = imageToBeDeleted.split("public/");
            String url = parts[0] + parts[1];
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpDelete post = new HttpDelete(url);
                post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + serviceKey);

                client.execute(post);
            } catch (IOException e) {
                logger.error("Message: {}, Cause: {}, StackTrace: {}", e.getMessage(), e.getCause(), Arrays.toString(e.getStackTrace()));
                throw new RuntimeException("Image Delete failed");
            }
        }

        repo.deleteById(prodId);
        return true;
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}

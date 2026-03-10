package com.amyaslab.eco_project.service;

import com.amyaslab.eco_project.model.Product;
import com.amyaslab.eco_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }
    public Product getProductById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);}

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageData(imageFile.getBytes());
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
        } else {
            // Keep original image data by fetching the existing product
            Product existingProduct = repo.findById(id).orElse(null);
            if (existingProduct != null) {
                product.setImageData(existingProduct.getImageData());
                product.setImageName(existingProduct.getImageName());
                product.setImageType(existingProduct.getImageType());
            }
        }
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}

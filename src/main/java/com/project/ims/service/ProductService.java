package com.project.ims.service;

import com.project.ims.model.dto.ProductDTO;
import com.project.ims.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ProductService {

    Product addProduct(ProductDTO product);
    Product findByProductId(int id);
    List<Product> filterProductsByName(String name);
    Page<Map<String, Object>> findAll(Pageable pageable);
    Product updateProduct(int id, ProductDTO productDTO);
    void deleteProduct(int id);
}

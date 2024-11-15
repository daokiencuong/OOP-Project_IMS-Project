package com.project.ims.service.impl;

import com.project.ims.model.dto.ProductDTO;
import com.project.ims.model.entity.Product;
import com.project.ims.repository.ProductRepository;
import com.project.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(ProductDTO productDTO) {
        Optional<Integer> existingProductIdOpt = productRepository.findProductIdByProductId(productDTO.getProductId());
        if (existingProductIdOpt.isPresent()) {
            Product existingProduct = productRepository.findById(productDTO.getProductId()).get();
            existingProduct.setQuantity(existingProduct.getQuantity() + productDTO.getQuantity());
            existingProduct.setLastUpdate(new Date());
            return productRepository.save(existingProduct);
        }
        Product newProduct = productDTO.constructFromDTO();
        newProduct.setLastUpdate(new Date());
        return productRepository.save(newProduct);
    }

    @Override
    public Product findByProductId(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found."));
    }

    @Override
    public List<Product> filterProductsByName(String name) {
        return productRepository.findByProductNameContaining(name);
    }

    @Override
    public Page<Map<String, Object>> findAll(Pageable pageable) {
        Page<Object[]> resultPage = productRepository.getAll(pageable);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] row : resultPage.getContent()) {
            Product product = (Product) row[0];
            Integer supplierID = (Integer) row[1];
            String supplierName = (String) row[2];
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("ProductID", product.getProductID());
            productMap.put("ProductName", product.getProductName());
            productMap.put("category", product.getCategory());
            productMap.put("price", product.getPrice());
            productMap.put("unitCal", product.getUnitCal());
            productMap.put("quantity", product.getQuantity());
            productMap.put("supplierID", supplierID);
            productMap.put("supplierName", supplierName);
            result.add(productMap);
        }

        return new PageImpl<>(result, pageable, resultPage.getTotalElements());
    }

    @Override
    public Product updateProduct(int id, ProductDTO productDTO) {
        Product existingProduct = findByProductId(id);
        if (productDTO.getProductName() != null) {
            existingProduct.setProductName(productDTO.getProductName());
        }
        if (productDTO.getCategory() != null) {
            existingProduct.setCategory(productDTO.getCategory());
        }
        if (productDTO.getPrice() != null) {
            existingProduct.setPrice(productDTO.getPrice());
        }
        if (productDTO.getUnitCal() != null) {
            existingProduct.setUnitCal(productDTO.getUnitCal());
        }
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product with ID " + id + " not found.");
        }
    }
}

package com.project.ims.controller;

import com.project.ims.model.dto.ProductDTO;
import com.project.ims.model.entity.Product;
import com.project.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/insert")
    public Product createProduct(@RequestBody  ProductDTO product) {
        return productService.addProduct(product);
    }

    @GetMapping("/search")
    public Product findProductById(@RequestParam int id) {
        return productService.findByProductId(id);
    }

    @GetMapping("/search/{id}")
    public Product findProductById2(@PathVariable int id) {
        return productService.findByProductId(id);
    }

    @GetMapping("/filter")
    public List<Product> findProductByName(@RequestParam String name) {
         return productService.filterProductsByName(name);
    }

    @GetMapping("/get-all")
    public Page<Product> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortBy = Sort.by(direction, sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        return productService.findAll(pageable);
    }


    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestParam int id) {
        productService.deleteProduct(id);
    }

}

package com.project.ims.model.dto;

import com.project.ims.model.entity.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private int productId;
    private String productName;
    private String category;
    private Double price;
    private String unitCal;
    private Integer quantity;


    public Product constructFromDTO() {
        Product product = new Product(productName, category, price, unitCal, quantity);
        product.setProductID(this.productId);
        return product;
    }

}



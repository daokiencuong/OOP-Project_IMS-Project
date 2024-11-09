package com.project.ims.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

/*
CREATE TABLE Product (
    productID INT PRIMARY KEY,
    productName VARCHAR(100),
    category VARCHAR(100),
    price DECIMAL(10, 2),
    unitCal VARCHAR(50)
);
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    @Column(name = "product_name", nullable = false)
    @NonNull
    private String productName;

    @Column(name = "category", nullable = false)
    @NonNull
    private String category;

    @Column(name = "price", nullable = false)
    @NonNull
    private Double price;
    
    @Column(name = "unit_cal", nullable = false)
    @NonNull
    private String unitCal;

    @Column(name = "last_update", nullable = true)
    private Date lastUpdate;

    
    @Column(name = "quantity", nullable = false, columnDefinition = "int default 1")
    @NonNull
    private Integer quantity;

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImport> productImports;

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductExport> productExports;

    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    private List<Supplier> suppliers;
}

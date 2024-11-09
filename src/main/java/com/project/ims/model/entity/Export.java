package com.project.ims.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/*
CREATE TABLE Export (
    exportID INT PRIMARY KEY,
    partnerID INT,
    TotalQuantity INT,
    date DATE,
    FOREIGN KEY (partnerID) REFERENCES Partner(partnerID)
);
 */

@Data
@Entity
@Table(name = "export")
public class Export {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exportID;

    @Column(name = "totalQuantity", nullable = false)
    private int totalQuantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "partnerID", nullable = false)
    private Partner partner;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "export_product",
//            joinColumns = @JoinColumn(name = "exportID"),
//            inverseJoinColumns = @JoinColumn(name = "productID")
//    )
//    private List<Product> products;
    @OneToMany(mappedBy = "exportEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductExport> productExports;
}
